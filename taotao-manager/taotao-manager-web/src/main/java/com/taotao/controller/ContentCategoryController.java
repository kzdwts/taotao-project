package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;

/**
 * 广告类别管理控制层
 * 
 * @author kangyong
 * @date 2018年7月7日
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;

	/**
	 * 广告类别列表
	 * 
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContengCategoryList(@RequestParam(value = "id", defaultValue = "0") long parentId) {
		return contentCategoryService.getCategoryList(parentId);
	}

	/**
	 * 新增一个类别
	 * 
	 * @param parentId 父节点id
	 * @param name     当前节点名称
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createContentCategory(long parentId, String name) {
		return contentCategoryService.insertContentCategory(parentId, name);
	}

}
