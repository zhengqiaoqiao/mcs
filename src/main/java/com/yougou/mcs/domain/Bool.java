package com.yougou.mcs.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yougou.mcs.base.Base;
import com.yougou.mcs.domain.filter.BaseFilter;

/**
 * <p>Title: Bool</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年4月24日
 */
public class Bool extends Base {

	private static final long serialVersionUID = -4857917046185846134L;
	private List<BaseFilter> must;
	@JsonProperty("must_not")
	private List<BaseFilter> mustNot;
	private List<BaseFilter> should;
	public List<BaseFilter> getMust() {
		return must;
	}
	public void setMust(List<BaseFilter> must) {
		this.must = must;
	}
	public List<BaseFilter> getMustNot() {
		return mustNot;
	}
	public void setMustNot(List<BaseFilter> mustNot) {
		this.mustNot = mustNot;
	}
	public List<BaseFilter> getShould() {
		return should;
	}
	public void setShould(List<BaseFilter> should) {
		this.should = should;
	}
}
