package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.RedisService;

/**
 * 缓存管理控制层
 * 
 * @author kangyong
 * @date 2018年7月8日
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisController {

	@Autowired
	private RedisService redisService;

	/**
	 * 同步首页大广告位缓存
	 * 
	 * @param contentCid
	 * @return
	 */
	@RequestMapping("/content/{contentCid}")
	public TaotaoResult contentCacheSync(@PathVariable Long contentCid) {
		return redisService.syncContent(contentCid);
	}

}
