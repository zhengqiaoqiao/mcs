package com.yougou.mcs.domain;

import com.yougou.mcs.base.Base;


/**
 * <p>Title: Shard</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年4月24日
 */
public class Shards extends Base {
	private static final long serialVersionUID = -593728803664097654L;
	//一共搜索多少个分片
	private int total;
	//成功的分片数量
	private int successful;
	//失败的分片数量
	private int failed;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getSuccessful() {
		return successful;
	}
	public void setSuccessful(int successful) {
		this.successful = successful;
	}
	public int getFailed() {
		return failed;
	}
	public void setFailed(int failed) {
		this.failed = failed;
	}
}
