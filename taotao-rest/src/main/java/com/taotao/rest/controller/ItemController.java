package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.ItemService;

/**
 * 商品管理控制层
 * 
 * @author kangyong
 * @date 2018年7月11日
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * 查询商品基本信息
	 * 
	 * @param itemId 商品id
	 * @return
	 */
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public TaotaoResult getItemBaseInfo(@PathVariable Long itemId) {
		return itemService.getItemBaseInfo(itemId);
	}

	/**
	 * 查询商品描述信息
	 * 
	 * @param itemId 商品id
	 * @return
	 */
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemDesc(@PathVariable Long itemId) {
		return itemService.getItemDesc(itemId);
	}

	/**
	 * 获取商品规格参数
	 * 
	 * @param itemId 商品id
	 * @return
	 */
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public TaotaoResult getItemParamItem(@PathVariable Long itemId) {
		return itemService.getItemParam(itemId);
	}

}
