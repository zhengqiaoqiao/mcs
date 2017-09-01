package com.yougou.mcs.domain;

import java.util.Date;

import com.yougou.mcs.base.Base;

/**
 * <p>Title: Source</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年4月24日
 */
public class Source extends Base {
	private static final long serialVersionUID = 344512992209717994L;
	private Date invokeTime;
	private String service;
	private String method;
	private String consumer;
	private String provider;
	private String type;
	private double success;
	private double failure;
	private double elapsed;
	private double concurrent;
	private int maxElapsed;
	private int maxConcurrent;
	public Date getInvokeTime() {
		return invokeTime;
	}
	public void setInvokeTime(Date invokeTime) {
		this.invokeTime = invokeTime;
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
	public String getConsumer() {
		return consumer;
	}
	public void setConsumer(String consumer) {
		this.consumer = consumer;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getSuccess() {
		return success;
	}
	public void setSuccess(double success) {
		this.success = success;
	}
	public double getFailure() {
		return failure;
	}
	public void setFailure(double failure) {
		this.failure = failure;
	}
	public double getElapsed() {
		return elapsed;
	}
	public void setElapsed(double elapsed) {
		this.elapsed = elapsed;
	}
	public double getConcurrent() {
		return concurrent;
	}
	public void setConcurrent(double concurrent) {
		this.concurrent = concurrent;
	}
	public int getMaxElapsed() {
		return maxElapsed;
	}
	public void setMaxElapsed(int maxElapsed) {
		this.maxElapsed = maxElapsed;
	}
	public int getMaxConcurrent() {
		return maxConcurrent;
	}
	public void setMaxConcurrent(int maxConcurrent) {
		this.maxConcurrent = maxConcurrent;
	}
}
