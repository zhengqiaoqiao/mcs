package com.yougou.mcs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yougou.mcs.base.BaseResponse;
import com.yougou.mcs.domain.Hits;
import com.yougou.mcs.domain.Shards;

/**
 * <p>Title: SearchResponse</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年4月20日
 */
public class SearchResponse extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8416194380398128189L;
	//Elasticsearch执行这个搜索的耗时，以毫秒为单位
	private int took;
	//指明这个搜索是否超时
	@JsonProperty("timed_out")
	private boolean timedOut;
	//指出多少个分片被搜索了，同时也指出了成功/失败的被搜索的shards的数量
	@JsonProperty("_shards")
	private Shards shards;
	//搜索结果
	private Hits hits;
	public int getTook() {
		return took;
	}
	public void setTook(int took) {
		this.took = took;
	}
	public boolean isTimedOut() {
		return timedOut;
	}
	public void setTimedOut(boolean timedOut) {
		this.timedOut = timedOut;
	}
	public Shards getShards() {
		return shards;
	}
	public void setShards(Shards shards) {
		this.shards = shards;
	}
	public Hits getHits() {
		return hits;
	}
	public void setHits(Hits hits) {
		this.hits = hits;
	}

}
