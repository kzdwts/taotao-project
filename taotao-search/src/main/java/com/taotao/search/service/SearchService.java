package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

/**
 * 搜索业务管理接口层
 * 
 * @author kangyong
 * @date 2018年7月9日
 */
public interface SearchService {

	/**
	 * 查询商品搜索商品
	 * 
	 * @param queryString 关键字符串
	 * @param page        当前页码
	 * @param rows        每页数量
	 * @return
	 * @throws Exception 
	 */
	SearchResult search(String queryString, int page, int rows) throws Exception;

}
