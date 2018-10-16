package com.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.taotao.search.pojo.SearchResult;

/**
 * 商品搜索持久层接口
 * 
 * @author kangyong
 * @date 2018年7月8日
 */
public interface SearchDao {

	/**
	 * 查询商品信息
	 * 
	 * @param query 条件
	 * @return
	 * @throws Exception
	 */
	SearchResult search(SolrQuery query) throws Exception;
}
