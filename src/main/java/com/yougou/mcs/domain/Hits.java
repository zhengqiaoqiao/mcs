package com.yougou.mcs.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yougou.mcs.base.Base;
/**
 * <p>Title: Hits</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年4月24日
 */
public class Hits extends Base {
	private static final long serialVersionUID = -6239792807781882885L;
	//能够匹配我们查询标准的文档的总数目
	private int total;
	//最高匹配率
	@JsonProperty("max_score")
	private double maxScore;
	//真正的搜索结果数据（默认只显示前10个文档）
	private List<Hit> hits;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}
	public List<Hit> getHits() {
		return hits;
	}
	public void setHits(List<Hit> hits) {
		this.hits = hits;
	}
}