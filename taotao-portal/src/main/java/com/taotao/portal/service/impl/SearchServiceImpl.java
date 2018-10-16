package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

/**
 * 搜索业务实现层
 * 
 * @author kangyong
 * @date 2018年7月10日
 */
@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;

	@Override
	public SearchResult search(String queryString, int page) {
		// 封装参数
		Map<String, String> param = new HashMap<>();
		param.put("q", queryString);
		param.put("page", page + "");
		try {
			// 调用taotao-search的服务执行查询
			String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
			// 转换成实体类
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
			if (taotaoResult.getStatus() == 200) {
				SearchResult result = (SearchResult) taotaoResult.getData();
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
