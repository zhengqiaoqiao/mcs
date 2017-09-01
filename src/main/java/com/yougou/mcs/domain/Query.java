package com.yougou.mcs.domain;

import com.yougou.mcs.base.Base;

/**
 * <p>Title: Query</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年4月24日
 */
public class Query extends Base{
	private static final long serialVersionUID = -3485191705521230224L;
	private Bool bool;
	public Bool getBool() {
		return bool;
	}
	public void setBool(Bool bool) {
		this.bool = bool;
	}
}
