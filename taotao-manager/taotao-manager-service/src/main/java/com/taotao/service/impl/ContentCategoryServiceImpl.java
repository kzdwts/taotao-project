package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

/**
 * 广告类别业务实现层
 * 
 * @author kangyong
 * @date 2018年7月7日
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		// 根据父类id，查询子节点集合
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);

		// 遍历结果集存入树形结构中
		List<EUTreeNode> resultList = new ArrayList<>();
		for (TbContentCategory contentCategory : list) {
			// 创建节点
			EUTreeNode node = new EUTreeNode();
			node.setId(contentCategory.getId());
			node.setText(contentCategory.getName());
			node.setState(contentCategory.getIsParent() ? "closed" : "open");
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public TaotaoResult insertContentCategory(long parentId, String name) {
		// 封装pojo
		Date date = new Date();
		TbContentCategory contentCategory = new TbContentCategory();
		// 该类目是否为父类目，1为true，0为false
		contentCategory.setIsParent(false);
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		// 状态。可选值:1(正常),2(删除)
		contentCategory.setStatus(1);
		contentCategory.setCreated(date);
		contentCategory.setUpdated(date);
		// 新增到数据库
		contentCategoryMapper.insert(contentCategory);

		// 查询父节点的isParent列是否为true，如果不是true改成true
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			// 更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		return TaotaoResult.ok(contentCategory);
	}

}
