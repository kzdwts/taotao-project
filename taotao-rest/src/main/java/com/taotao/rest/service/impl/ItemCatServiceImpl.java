package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * 商品类别管理业务实现层
 * 
 * @author kangyong
 * @date 2018年7月7日
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${INDEX_ITEM_CAT_REDIS_KEY}")
	private String INDEX_ITEM_CAT_REDIS_KEY;

	@Override
	public CatResult getItemCatList() {
		// 从缓存中取数据
		try {
			String result = jedisClient.get(INDEX_ITEM_CAT_REDIS_KEY);
			if (!StringUtils.isBlank(result)) {
				CatResult catResult = JsonUtils.jsonToPojo(result, CatResult.class);
				return catResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		CatResult result = new CatResult();
		// 查询分类列表，父节点初始为0
		result.setData(getCatList(0));

		// 数据存入缓存
		try {
			String cacheString = JsonUtils.objectToJson(result);
			jedisClient.set(INDEX_ITEM_CAT_REDIS_KEY, cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 查询商品分类列表
	 * 
	 * @param parentId 父节点id
	 * @return
	 */
	private List<?> getCatList(long parentId) {
		// 创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);

		// 返回的结果集
		List resultList = new ArrayList<>();
		// 遍历查询结果集，向resultList中添加节点
		int count = 0;
		for (TbItemCat tbItemCat : list) {
			// 判断是否为父节点
			if (tbItemCat.getIsParent()) {
				// 创建节点
				CatNode catNode = new CatNode();
				if (parentId == 0) {
					// 第一层节点(父节点)
					catNode.setName(
							"<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
				} else {
					// 其它层节点(父节点)
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/" + tbItemCat.getId() + "");
				catNode.setItem(getCatList(tbItemCat.getId()));
				resultList.add(catNode);

				// 只取前14行
				count++;
				if (count >= 14) {
					break;
				}
			} else {
				// 叶子节点
				resultList.add("/products/" + tbItemCat.getId() + "|" + tbItemCat.getName());
			}
		}

		return resultList;
	}

}
