package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 缓存处理业务层接口
 * 
 * @author kangyong
 * @date 2018年7月8日
 */
public interface RedisService {

	/**
	 * 同步首页大广告位缓存
	 * 
	 * @param contentCid
	 * @return
	 */
	TaotaoResult syncContent(long contentCid);
}
