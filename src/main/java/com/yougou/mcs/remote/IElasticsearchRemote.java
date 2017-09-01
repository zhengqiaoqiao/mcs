package com.yougou.mcs.remote;

import com.yougou.mcs.request.SearchRequest;
import com.yougou.mcs.response.SearchResponse;

import feign.RequestLine;

/**
 * <p>Title: EsRemote</p>
 * <p>Description: http://10.10.10.88:9200/dubbo.invoke/_search</p>
 * @author: zheng.qq
 * @date: 2017年4月20日
 */
public interface IElasticsearchRemote {
	/**
	 * 检索
	 * @param request
	 * @return
	 */
    @RequestLine("POST /_search")
    SearchResponse search(SearchRequest request);
}
