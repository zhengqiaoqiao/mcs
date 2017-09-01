package com.yougou.mcs.domain.term;

/**
 * <p>Title: ServiceTerm</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年4月24日
 */
public class ServiceTerm extends BaseTerm {

	private static final long serialVersionUID = -3265756671877715180L;
	private String service;
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
}
