package com.yougou.mcs.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yougou.mcs.base.BaseRequest;

/**
 * <p>Title: SearchRequest</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年4月20日
 */
public class SearchRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4590164674942122102L;
	/**
	 * 开始位置
	 */
    @JsonProperty("from")
    private int from;
    /**
	 * 返回数量
	 */
    @JsonProperty("size")
    private int size;
    /**
	 * 排序
	 */
    @JsonProperty("sort")
    private String[] sort;
    /**
	 * 
	 */
    @JsonProperty("aggs")
    private String aggs;
    /**
	 * 筛选条件
	 */
    @JsonProperty("query")
    private Object query;
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String[] getSort() {
		return sort;
	}
	public void setSort(String[] sort) {
		this.sort = sort;
	}
	public String getAggs() {
		return aggs;
	}
	public void setAggs(String aggs) {
		this.aggs = aggs;
	}
	public Object getQuery() {
		return query;
	}
	public void setQuery(Object query) {
		this.query = query;
	}
	
}
