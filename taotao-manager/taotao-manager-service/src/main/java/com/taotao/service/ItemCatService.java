package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.TreeNode;

/**
 * 商品类别管理业务接口层
 * 
 * @author kangyong
 * @date 2018年7月5日
 */
public interface ItemCatService {

	/**
	 * 查询商品类别列表
	 * 
	 * @param parentId 类别父类id
	 * @return
	 */
	List<TreeNode> getItemCatList(long parentId);
}
