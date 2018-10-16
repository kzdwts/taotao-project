package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

/**
 * 商品类别管理业务实现层
 * 
 * @author kangyong
 * @date 2018年7月5日
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<TreeNode> getItemCatList(long parentId) {
		// 封装查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);

		// 封装查询结果
		List<TreeNode> resultList = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			// 创建树节点
			TreeNode node = new TreeNode(tbItemCat.getId(), tbItemCat.getName(),
					tbItemCat.getIsParent() ? "closed" : "open");
			resultList.add(node);
		}
		return resultList;
	}

}
