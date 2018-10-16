package com.taotao.portal.service;

import com.taotao.pojo.TbItem;

/**
 * 商品信息业务接口
 * 
 * @author kangyong
 * @date 2018年7月11日
 */
public interface ItemService {

	/**
	 * 商品基本信息
	 * 
	 * @param itemId 商品id
	 * @return
	 */
	TbItem getItemById(Long itemId);

	/**
	 * 获取商品描述信息
	 * 
	 * @param itemId 商品id
	 * @return
	 */
	String getItemDescById(long itemId);

	/**
	 * 获取商品规格参数
	 * 
	 * @param itemId 商品id
	 * @return
	 */
	String getItemParam(long itemId);

}
