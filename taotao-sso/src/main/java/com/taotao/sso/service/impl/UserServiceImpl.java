package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.UserService;

/**
 * 用户信息管理业务实现层
 * 
 * @author kangyong
 * @date 2018年7月12日
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;

	@Override
	public TaotaoResult checkData(String content, Integer type) {
		// 创建查询条件
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		// type：参数1、2、3分别代表username、phone、email
		if (1 == type) {
			criteria.andUsernameEqualTo(content);
		} else if (2 == type) {
			criteria.andPhoneEqualTo(content);
		} else {
			criteria.andEmailEqualTo(content);
		}

		// 执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return TaotaoResult.ok(true);
		}

		return TaotaoResult.ok(false);
	}

	@Override
	public TaotaoResult createUser(TbUser user) {
		// 补全pojo
		user.setCreated(new Date());
		user.setUpdated(new Date());
		// md5密码加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		// 执行插入
		userMapper.insert(user);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		// 查询条件
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);

		if (null == list || list.size() == 0) {
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		// 取出查询结果
		TbUser user = list.get(0);
		// 比对密码
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			return TaotaoResult.build(400, "用户名或密码错误");
		}

		// 生成token
		String token = UUID.randomUUID().toString();
		// 保存用户信息之前，把密码清空
		user.setPassword(null);
		// 将用户信息写入session
		jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
		// 添加cookie的逻辑，cookie的有效期是关闭浏览器
		CookieUtils.setCookie(request, response, "TT_TOKEN", token);
		
		// 返回token
		return TaotaoResult.ok(token);
	}

	@Override
	public TaotaoResult getUserByToken(String token) {
		// 根据token从redis中查询用户信息
		String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
		// 判断是否为空
		if (StringUtils.isBlank(json)) {
			return TaotaoResult.build(400, "此session已过期，请重新登录");
		}
		// 更新过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
		// 返回用户信息
		return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}

}
