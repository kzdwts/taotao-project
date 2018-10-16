package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * 广告内容业务层接口
 * 
 * @author kangyong
 * @date 2018年7月7日
 */
public interface ContentService {

	/**
	 * 根据类别id，查询广告列表
	 * 
	 * @param categoryId 类别id
	 * @param page       当前页码
	 * @param rows       每页数量
	 * @return
	 */
	EUDataGridResult getContentList(long categoryId, int page, int rows);

	/**
	 * 新增广告内容
	 * 
	 * @param content 广告内容详情
	 * @return
	 */
	TaotaoResult insertContent(TbContent content);

}
