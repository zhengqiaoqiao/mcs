package com.yougou.mcs.domain.range;

/**
 * <p>Title: InvokeTimeRange</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年4月24日
 */
public class InvokeTimeRange extends BaseRange {

	private static final long serialVersionUID = -3265756671877715180L;
	private Range invokeTime;
	public Range getInvokeTime() {
		return invokeTime;
	}
	public void setInvokeTime(Range invokeTime) {
		this.invokeTime = invokeTime;
	}
	
}
