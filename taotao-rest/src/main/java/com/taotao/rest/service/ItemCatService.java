package com.taotao.rest.service;

import com.taotao.rest.pojo.CatResult;

/**
 * 商品类别业务层接口
 * 
 * @author kangyong
 * @date 2018年7月7日
 */
public interface ItemCatService {

	/**
	 * 查询分类列表
	 * 
	 * @return
	 */
	CatResult getItemCatList();
	
}
