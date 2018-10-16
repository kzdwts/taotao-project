package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * 商品类别控制层
 * 
 * @author kangyong
 * @date 2018年7月7日
 */
@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

	/**
	 * 商品类别列表
	 * 
	 * @param callback 回调方法
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/itemcat/list", produces =
	 * MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	 * 
	 * @ResponseBody private String getItemCatList(String callback) { // 查询
	 * CatResult list = itemCatService.getItemCatList(); // 转成json字符串 String
	 * jsonString = JsonUtils.objectToJson(list);
	 * 
	 * String result = callback + "(" + jsonString + ")"; return result; }
	 */

	@RequestMapping(value = "/itemcat/list")
	@ResponseBody
	public Object getItemCatList(String callback) {
		// 查询
		CatResult result = itemCatService.getItemCatList();
		// 返回对象
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}

}
