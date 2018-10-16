package com.taotao.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;

/**
 * 搜索控制层
 * 
 * @author kangyong
 * @date 2018年7月9日
 */
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	/**
	 * 搜索
	 * 
	 * @param queryString 关键词
	 * @param page        当前页码
	 * @param rows        每页数量
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public TaotaoResult search(@RequestParam("q") String queryString, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "60") Integer rows) {
		// 查询条件不能为空
		if (StringUtils.isBlank(queryString)) {
			return TaotaoResult.build(400, "查询条件不能为空");
		}

		// 搜索结果
		SearchResult result = null;
		try {
			queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
			result = searchService.search(queryString, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok(result);
	}

}
