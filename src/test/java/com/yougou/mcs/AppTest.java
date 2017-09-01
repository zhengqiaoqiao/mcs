package com.yougou.mcs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.yougou.mcs.App;
import com.yougou.mcs.domain.Bool;
import com.yougou.mcs.domain.Query;
import com.yougou.mcs.domain.filter.BaseFilter;
import com.yougou.mcs.domain.filter.RangeFilter;
import com.yougou.mcs.domain.filter.TermFilter;
import com.yougou.mcs.domain.range.InvokeTimeRange;
import com.yougou.mcs.domain.range.Range;
import com.yougou.mcs.domain.term.ServiceTerm;
import com.yougou.mcs.remote.IElasticsearchRemote;
import com.yougou.mcs.request.SearchRequest;
import com.yougou.mcs.response.SearchResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={App.class})
public class AppTest {
	@Autowired
    private IElasticsearchRemote elasticsearchRemote;
	@Before
	public void before() {

	}
	@After
	public void after() {
		
	}
	@Test
	public void test1() {
		String jarFile = "E:\\cds-api-0.0.1-SNAPSHOT.jar";
		List<BaseFilter> filters = new ArrayList<BaseFilter>();
		ServiceTerm term = new ServiceTerm();
		term.setService("com.yougou.active.api.IActiveApiService");
		TermFilter termFilter = new TermFilter();
		termFilter.setTerm(term);
		filters.add(termFilter);
		
		
		Date now = new Date();
		DateTime dateTime = new DateTime(now);
		Date gt = dateTime.minusMonths(12).toDate();
		Date lt = dateTime.minusMonths(9).toDate();
		Range range = new Range();
		range.setGt(gt);
		range.setLt(lt);
		
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
		request.setSize(20);
		request.setQuery(query);
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(request));
		
		SearchResponse response = elasticsearchRemote.search(request);
		
		System.out.println(response.toString());
	}
}
