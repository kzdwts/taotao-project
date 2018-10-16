package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

/**
 * 用户信息业务层接口
 * 
 * @author kangyong
 * @date 2018年7月13日
 */
public interface UserService {

	/**
	 * 根据token获取用户信息
	 * 
	 * @param token
	 * @return
	 */
	TbUser getUserByToken(String token);
	
}
