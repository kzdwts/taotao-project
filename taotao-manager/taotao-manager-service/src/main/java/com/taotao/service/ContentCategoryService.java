package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;

/**
 * 广告内容分类业务层接口
 * 
 * @author kangyong
 * @date 2018年7月7日
 */
public interface ContentCategoryService {

	/**
	 * 获取类别列表
	 * 
	 * @param parentId 父类id
	 * @return
	 */
	List<EUTreeNode> getCategoryList(long parentId);

	/**
	 * 新增广告类别
	 * 
	 * @param parentId 父类id
	 * @param name     名称
	 * @return
	 */
	TaotaoResult insertContentCategory(long parentId, String name);

}
