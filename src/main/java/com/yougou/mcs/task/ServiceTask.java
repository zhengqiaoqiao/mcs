package com.yougou.mcs.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import com.google.gson.Gson;
import com.yougou.mcs.domain.Bool;
import com.yougou.mcs.domain.Query;
import com.yougou.mcs.domain.Statistics;
import com.yougou.mcs.domain.filter.BaseFilter;
import com.yougou.mcs.domain.filter.RangeFilter;
import com.yougou.mcs.domain.filter.TermFilter;
import com.yougou.mcs.domain.range.InvokeTimeRange;
import com.yougou.mcs.domain.range.Range;
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
public class ServiceTask implements Runnable {
	private CountDownLatch countDownLatch;
	private IElasticsearchRemote elasticsearchRemote;
	private String service;
	private Date startTime;
	private Date endTime;
	private ConcurrentHashMap<String, Statistics> map;
	
	public ServiceTask(ConcurrentHashMap<String, Statistics> map, CountDownLatch countDownLatch){
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
			ServiceTerm term = new ServiceTerm();
			term.setService(service);
			TermFilter termFilter = new TermFilter();
			termFilter.setTerm(term);
			filters.add(termFilter);
			
			Range range = new Range();
			range.setGt(startTime);
			range.setLt(endTime);
			
			InvokeTimeRange invokeTimeRange = new InvokeTimeRange();
			invokeTimeRange.setInvokeTime(range);
			
			RangeFilter rangeFilter = new RangeFilter();
			rangeFilter.setRange(invokeTimeRange);
			filters.add(rangeFilter);
			
			Bool bool = new Bool();
			bool.setMust(filters);
			
			Query query = new Query();
			query.setBool(bool);
			SearchRequest request = new SearchRequest();
			request.setFrom(0);
			request.setSize(1);
			request.setQuery(query);
			
			Gson gson = new Gson();
			System.out.println(gson.toJson(request));
			
			SearchResponse response = elasticsearchRemote.search(request);
			
			if(map!=null){
				if(!map.containsKey(service)){
					map.put(service, new Statistics());
				}
				synchronized (map.get(service)) {
					map.get(service).setCount(map.get(service).getCount() + response.getHits().getTotal());
				}
			}
			System.out.println(response.toString());
		}catch(Exception e){
			System.err.println(e.getMessage());
		}finally{
			countDownLatch.countDown();
		}
		
	}
	
}
