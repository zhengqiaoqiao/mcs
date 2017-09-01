package com.yougou.mcs.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yougou.mcs.domain.Bool;
import com.yougou.mcs.domain.Query;
import com.yougou.mcs.domain.Statistics;
import com.yougou.mcs.domain.filter.BaseFilter;
import com.yougou.mcs.domain.filter.RangeFilter;
import com.yougou.mcs.domain.filter.TermFilter;
import com.yougou.mcs.domain.range.InvokeTimeRange;
import com.yougou.mcs.domain.range.Range;
import com.yougou.mcs.domain.term.MethodTerm;
import com.yougou.mcs.domain.term.ServiceTerm;
import com.yougou.mcs.remote.IElasticsearchRemote;
import com.yougou.mcs.request.SearchRequest;
import com.yougou.mcs.response.SearchResponse;

/**
 * <p>Title: MethodTask</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年4月26日
 */
public class MethodTask implements Runnable {
	private final static Logger LOGGER = LoggerFactory.getLogger(MethodTask.class);
	private CountDownLatch countDownLatch;
	private IElasticsearchRemote elasticsearchRemote;
	private String service;
	private String method;
	private Date startTime;
	private Date endTime;
	private ConcurrentHashMap<String, Statistics> map;
	
	public MethodTask(ConcurrentHashMap<String, Statistics> map, CountDownLatch countDownLatch){
        this.map = map;
        this.countDownLatch = countDownLatch;
    }
	
	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}
	public void setCountDownLatch(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}
	public IElasticsearchRemote getElasticsearchRemote() {
		return elasticsearchRemote;
	}
	public void setElasticsearchRemote(IElasticsearchRemote elasticsearchRemote) {
		this.elasticsearchRemote = elasticsearchRemote;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public ConcurrentHashMap<String, Statistics> getMap() {
		return map;
	}
	public void setMap(ConcurrentHashMap<String, Statistics> map) {
		this.map = map;
	}
	public void run() {
		try{
			List<BaseFilter> filters = new ArrayList<BaseFilter>();
			ServiceTerm term1 = new ServiceTerm();
			term1.setService(service);
			TermFilter termFilter1 = new TermFilter();
			termFilter1.setTerm(term1);
			
			MethodTerm term2 = new MethodTerm();
			term2.setMethod(method);
			TermFilter termFilter2 = new TermFilter();
			termFilter2.setTerm(term2);
			filters.add(termFilter2);
			
			//是否设置时间过滤条件
			if(startTime==null && endTime==null){
				
			}else{
				Range range = new Range();
				range.setGt(startTime);
				range.setLt(endTime);
				
				InvokeTimeRange invokeTimeRange = new InvokeTimeRange();
				invokeTimeRange.setInvokeTime(range);
				
				RangeFilter rangeFilter = new RangeFilter();
				rangeFilter.setRange(invokeTimeRange);
				filters.add(rangeFilter);
			}
			
			
			Bool bool = new Bool();
			bool.setMust(filters);
			
			Query query = new Query();
			query.setBool(bool);
			SearchRequest request = new SearchRequest();
			request.setFrom(0);
			request.setSize(1);
			request.setQuery(query);
			
			LOGGER.info("发送请求：{}", request);
			
			SearchResponse response = elasticsearchRemote.search(request);
			
			LOGGER.info("返回结果：{}", response);
			if(map!=null){
				String key = service + "." + method;
				if(!map.containsKey(key)){
					Statistics statistics = new Statistics();
					statistics.setService(service);
					statistics.setMethod(method);
					statistics.setCount(0);
					map.put(key, statistics);
				}else{
					map.get(key).setRemark("(多)");
				}
				synchronized (map.get(key)) {
					map.get(key).setCount(map.get(key).getCount() + response.getHits().getTotal());
				}
			}
		}catch(Exception e){
			LOGGER.error("任务执行发生异常：服务名：{}, 方法名{}, 查询开始时间：{}, 查询结束时间：{}", service, method, startTime, endTime, e);
		}finally{
			countDownLatch.countDown();
		}
		
	}
	
}
