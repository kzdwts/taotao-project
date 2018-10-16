package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItem;
import com.taotao.portal.service.ItemService;

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
	 * 商品基本信息
	 * 
	 * @param itemId 商品id
	 * @param model
	 * @return
	 */
	@RequestMapping("/{itemId}")
	public String showItem(@PathVariable Long itemId, Model model) {
		// 查询
		TbItem item = itemService.getItemById(itemId);
		model.addAttribute("item", item);
		// 返回视图
		return "item";
	}

	/**
	 * 获取商品详情
	 * 
	 * @param itemId 商品id
	 * @return
	 */
	@RequestMapping(value = "/desc/{itemId}", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId) {
		return itemService.getItemDescById(itemId);
	}

	/**
	 * 查询规格参数
	 * 
	 * @param itemId 商品id
	 * @return
	 */
	@RequestMapping(value = "/param/{itemId}", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId) {
		return itemService.getItemParam(itemId);
	}

}
