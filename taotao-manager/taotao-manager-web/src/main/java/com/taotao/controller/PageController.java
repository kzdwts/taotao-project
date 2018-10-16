package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 进入商城后台管理页面
 * 
 * @author kangyong
 * @date 2018年7月5日
 */
@Controller
public class PageController {

	/**
	 * 进入首页
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}

	/**
	 * 展示其他页面
	 * 
	 * @param page 页面名称
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}

}
