package com.taotao.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

/**
 * 搜索服务控制层
 * 
 * @author kangyong
 * @date 2018年7月10日
 */
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	/**
	 * 商品搜索
	 * 
	 * @param queryString 关键词
	 * @param page        当前页码
	 * @param model       视图
	 * @return
	 */
	@RequestMapping("/search")
	public String search(@RequestParam("q") String queryString,
			@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
		// 查询字符串处理
		try {
			queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 查询
		SearchResult result = searchService.search(queryString, page);
		// 向页面传递参数
		model.addAttribute("query", queryString);
		model.addAttribute("totalPages", result.getPageCount());
		model.addAttribute("itemList", result.getItemList());
		model.addAttribute("page", page);
		System.out.println(result.getItemList().size());
		return "search";
	}

}
