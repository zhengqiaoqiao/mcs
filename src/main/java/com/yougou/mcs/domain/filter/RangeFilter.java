package com.yougou.mcs.domain.filter;

import com.yougou.mcs.domain.range.BaseRange;

/**
 * <p>Title: RangeFilter</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年4月24日
 */
public class RangeFilter extends BaseFilter {
	private static final long serialVersionUID = -7105833606549270569L;
	private BaseRange range;
	public BaseRange getRange() {
		return range;
	}
	public void setRange(BaseRange range) {
		this.range = range;
	}
	
}
