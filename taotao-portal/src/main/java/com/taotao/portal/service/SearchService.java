package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * 搜索业务接口层
 * 
 * @author kangyong
 * @date 2018年7月10日
 */
public interface SearchService {

	/**
	 * 关键词搜索
	 * 
	 * @param queryString 关键词
	 * @param page        当前页码
	 * @return
	 */
	SearchResult search(String queryString, int page);

}
