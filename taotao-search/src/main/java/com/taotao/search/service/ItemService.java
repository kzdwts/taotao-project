package com.taotao.search.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 商品信息业务层接口
 * 
 * @author kangyong
 * @date 2018年7月8日
 */
public interface ItemService {

	/**
	 * 导入所有商品信息
	 * 
	 * @return
	 */
	TaotaoResult importAllItems();
}
