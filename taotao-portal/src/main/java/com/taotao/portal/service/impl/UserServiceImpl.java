package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;

/**
 * 用户信息业务实现层
 * 
 * @author kangyong
 * @date 2018年7月13日
 */
@Service
public class UserServiceImpl implements UserService {

	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	@Value("${SSO_USER_TOKEN}")
	public String SSO_USER_TOKEN;
	@Value("${SSO_DOMAIN_BASE_USRL}")
	public String SSO_DOMAIN_BASE_USRL;
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;

	@Override
	public TbUser getUserByToken(String token) {
		// 调用sso系统的服务，根据用户信息获取token
		try {
			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
			// 将json转换成TaotaoResult
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbUser.class);
			if (taotaoResult.getStatus() == 200) {
				TbUser user = (TbUser) taotaoResult.getData();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
