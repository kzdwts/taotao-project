package com.taotao.service;

/**
 * 商品规格参数业务层接口
 * 
 * @author kangyong
 * @date 2018年7月7日
 */
public interface ItemParamItemService {

	/**
	 * 获取商品规格参数
	 * 
	 * @param itemId 商品id
	 * @return
	 */
	String getItemParamByItemId(long itemId);

}
