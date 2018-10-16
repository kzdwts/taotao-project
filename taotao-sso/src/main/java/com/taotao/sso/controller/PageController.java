package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 单点登录系统页面控制层
 * 
 * @author kangyong
 * @date 2018年7月13日
 */
@Controller
@RequestMapping("/page")
public class PageController {

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String showLogin(String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}

	/**
	 * 注册页面
	 * 
	 * @return
	 */
	@RequestMapping("/register")
	public String showRegister() {
		return "register";
	}

}
