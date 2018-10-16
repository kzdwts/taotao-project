package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.service.ItemParamItemService;

/**
 * 商品规格参数控制层
 * 
 * @author kangyong
 * @date 2018年7月7日
 */
@Controller
@RequestMapping("/item/param/item")
public class ItemParamItemController {

	@Autowired
	private ItemParamItemService itemParamItemService;

	/**
	 * 根据商品id，获取商品规格参数
	 * 
	 * @param itemId 商品id
	 * @return
	 */
	@RequestMapping("/{itemId}")
	public String getItemParamData(@PathVariable Long itemId, Model model) {
		// 查询
		String paramData = itemParamItemService.getItemParamByItemId(itemId);
		model.addAttribute("paramData", paramData);
		return "item";
	}

}
