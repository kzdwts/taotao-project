package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;

/**
 * 商品规格参数模板控制层
 * 
 * @author kangyong
 * @date 2018年7月7日
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;

	/**
	 * 根据类别id，查询商品规格参数模板
	 * 
	 * @param itemCatId 类别id
	 * @return
	 */
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaotaoResult getItemParamByCid(@PathVariable Long itemCatId) {
		return itemParamService.getItemParamByCid(itemCatId);
	}

	/**
	 * 新增规格参数模板
	 * 
	 * @param cid       商品类别id
	 * @param paramData 类别规格参数
	 * @return
	 */
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable Long cid, String paramData) {
		return itemParamService.insertItemParam(cid, paramData);
	}

}
