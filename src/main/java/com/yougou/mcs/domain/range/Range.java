package com.yougou.mcs.domain.range;

import java.util.Date;

import com.yougou.mcs.base.Base;

/**
 * <p>Title: BaseTerm</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年4月24日
 */
public class Range extends Base {
	private static final long serialVersionUID = -7550061276936050446L;
	private Date gt;
	private Date lt;
	public Date getGt() {
		return gt;
	}
	public void setGt(Date gt) {
		this.gt = gt;
	}
	public Date getLt() {
		return lt;
	}
	public void setLt(Date lt) {
		this.lt = lt;
	}
}
