package com.yougou.mcs.metric.counter;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;

/**
 * <p>Title: BaseCounter</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年8月30日
 */
public class BaseCounter extends Counter{

	public BaseCounter(MetricRegistry registry, String name){
		registry.counter(name);
	}
	
	public void inc() {
		this.inc();
    }
	
	public void dec() {
		this.dec();
    }
}
