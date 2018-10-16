package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TreeNode;
import com.taotao.service.ItemCatService;

/**
 * 商品类别控制层
 * 
 * @author kangyong
 * @date 2018年7月5日
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

	/**
	 * 查询商品类别列表
	 * 
	 * @param parentId 父类别id
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<TreeNode> getItemCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		return itemCatService.getItemCatList(parentId);
	}
}
