package com.yougou.mcs.domain;

import com.yougou.mcs.base.Base;

/**
 * <p>Title: Statistics</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年4月26日
 */
public class Statistics extends Base{

	private static final long serialVersionUID = 4680109982958549205L;
	//接口名
	private String service;
	//方法名
	private String method;
	//命中次数
	private long count;
	//备注
	private String remark;
	
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
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
