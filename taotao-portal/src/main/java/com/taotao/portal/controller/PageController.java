package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.portal.service.ContentService;

/**
 * 页面初始跳转控制层
 * 
 * @author kangyong
 * @date 2018年7月7日
 */
@Controller
public class PageController {

	@Autowired
	private ContentService contentService;

	/**
	 * 进入首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String showIndex(Model model) {
		// 查询
		String contentList = contentService.getContentList();
		model.addAttribute("ad1", contentList);
		return "index";
	}

}
