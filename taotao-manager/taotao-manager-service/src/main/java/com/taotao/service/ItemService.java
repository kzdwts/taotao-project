package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

/**
 * 商品管理业务层接口
 * 
 * @author kangyong
 * @date 2018年7月5日
 */
public interface ItemService {

	/**
	 * 根据id查询商品
	 * 
	 * @param itemId 商品id
	 * @return
	 */
	TbItem getItemById(long itemId);

	/**
	 * 查询商品列表
	 * 
	 * @param page 页码
	 * @param rows 每页数量
	 * @return
	 */
	EUDataGridResult getItemList(int page, int rows);

	/**
	 * 新增一个商品
	 * 
	 * @param item     商品
	 * @param itemDesc 商品描述
	 * @return
	 */
	TaotaoResult addItem(TbItem item, String desc, String itemParams);
}
