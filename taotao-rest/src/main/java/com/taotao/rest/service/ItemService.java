package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 商品信息管理业务层接口
 * 
 * @author kangyong
 * @date 2018年7月11日
 */
public interface ItemService {

	/**
	 * 获取商品基本详情
	 * 
	 * @param itemId 商品id
	 * @return
	 */
	TaotaoResult getItemBaseInfo(Long itemId);

	/**
	 * 获取商品描述信息
	 * 
	 * @param itemId 商品id
	 * @return
	 */
	TaotaoResult getItemDesc(Long itemId);

	/**
	 * 获取商品规格参数
	 * 
	 * @param itemId 商品id
	 * @return
	 */
	TaotaoResult getItemParam(long itemId);

}
