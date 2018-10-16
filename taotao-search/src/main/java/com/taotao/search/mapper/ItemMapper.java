package com.taotao.search.mapper;

import java.util.List;

import com.taotao.search.pojo.Item;

/**
 * 商品查询mapper层
 * 
 * @author kangyong
 * @date 2018年7月8日
 */
public interface ItemMapper {

	/**
	 * 获取商品列表
	 * 
	 * @return
	 */
	List<Item> getItemList();
	
}
