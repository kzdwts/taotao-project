package com.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.impl.UserServiceImpl;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserServiceImpl userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 在handler执行之前处理

		// 判断用户是否登录
		// 从cookie取token
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		// 根据token获取用户信息
		TbUser user = userService.getUserByToken(token);
		// 取不到用户信息
		if (null == user) {
			// 跳转到登录页面，把用户请求的url传递到登录页面
			response.sendRedirect(
					userService.SSO_DOMAIN_BASE_USRL + userService.SSO_PAGE_LOGIN + "?redirect=" + request.getRequestURL());
			// 返回false
			return false;
		}
		// 取到用户信息，放行
		request.setAttribute("user", user);
		// 返回值决定handler是否执行。true：执行，false不执行。
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 在handler执行之后，返回ModelAndView之前

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 返回ModelAndView之后

	}

}
