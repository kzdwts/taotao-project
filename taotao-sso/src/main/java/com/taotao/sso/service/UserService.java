package com.taotao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * 用户信息管理接口层
 * 
 * @author kangyong
 * @date 2018年7月12日
 */
public interface UserService {

	/**
	 * 查询用户信息
	 * 
	 * @param content 内容
	 * @param type    类型（参数1、2、3分别代表username、phone、email）
	 * @return
	 */
	TaotaoResult checkData(String content, Integer type);

	/**
	 * 注册新用户
	 * 
	 * @param user 用户信息
	 * @return
	 */
	TaotaoResult createUser(TbUser user);

	/**
	 * 用户登录
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 根据token获取用户信息
	 * 
	 * @param token
	 * @return
	 */
	TaotaoResult getUserByToken(String token);

}
