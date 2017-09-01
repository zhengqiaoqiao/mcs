package com.yougou.mcs.domain.filter;

import com.yougou.mcs.domain.term.BaseTerm;

/**
 * <p>Title: TermFilter</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年4月24日
 */
public class TermFilter extends BaseFilter {
	private static final long serialVersionUID = -7105833606549270569L;
	private BaseTerm term;
	public BaseTerm getTerm() {
		return term;
	}
	public void setTerm(BaseTerm term) {
		this.term = term;
	}
}
