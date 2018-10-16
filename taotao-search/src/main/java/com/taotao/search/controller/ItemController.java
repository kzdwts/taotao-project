package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.ItemService;

/**
 * 搜索服务商品管理控制层
 * 
 * @author kangyong
 * @date 2018年7月8日
 */
@Controller
@RequestMapping("/manager")
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * 导入商品数据到索引库
	 * 
	 * @return
	 */
	@RequestMapping("/importall")
	@ResponseBody
	public TaotaoResult importAllItems() {
		return itemService.importAllItems();
	}
}
