package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;

/**
 * 商品管理控制层
 * 
 * @author kangyong
 * @date 2018年7月5日
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * 根据商品id，查询详情
	 * 
	 * @param itemId 商品id
	 * @return
	 */
	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		return itemService.getItemById(itemId);
	}

	/**
	 * 查询商品列表
	 * 
	 * @param page 页码
	 * @param rows 每页数量
	 * @return 分页数据
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(int page, int rows) {
		return itemService.getItemList(page, rows);
	}

	/**
	 * 新增商品
	 * 
	 * @param item 商品信息
	 * @param desc 商品描述信息
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult addItem(TbItem item, String desc, String itemParams) {
		return itemService.addItem(item, desc, itemParams);
	}

}
