package com.yougou.mcs.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yougou.mcs.base.Base;
/**
 * <p>Title: Hit</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年4月24日
 */
public class Hit extends Base{
	private static final long serialVersionUID = 2215017774488251282L;
	//文档存储的地方
	@JsonProperty("_index")
	private String index;
	//文档代表的对象的类
	@JsonProperty("_type")
	private String type;
	//文档的唯一标识
	private String id;
	//匹配率
	@JsonProperty("_score")
	private double score;
	//请求指定字段
	@JsonProperty("_source")
	private Source source;
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public Source getSource() {
		return source;
	}
	public void setSource(Source source) {
		this.source = source;
	}
}
