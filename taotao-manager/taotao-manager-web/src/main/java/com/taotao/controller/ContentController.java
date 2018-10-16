package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

/**
 * 广告内容管理控制层
 * 
 * @author kangyong
 * @date 2018年7月7日
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;

	/**
	 * 查询当前类别下的广告
	 * 
	 * @param categoryId 广告id
	 * @param page       当前页码
	 * @param rows       每页数目
	 * @return
	 */
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult queryContentByCid(Long categoryId, int page, int rows) {
		return contentService.getContentList(categoryId, page, rows);
	}

	/**
	 * 新增广告
	 * 
	 * @param content
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content) {
		return contentService.insertContent(content);
	}

}
