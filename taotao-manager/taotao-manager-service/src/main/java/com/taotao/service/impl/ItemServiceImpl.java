package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

/**
 * 商品管理业务实现层
 * 
 * @author kangyong
 * @date 2018年7月5日
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public TbItem getItemById(long itemId) {
		// 封装查询条件
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		// 执行查询
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem tbItem = list.get(0);
			return tbItem;
		}

		return null;
	}

	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		// 封装查询条件
		TbItemExample example = new TbItemExample();
		// 设置页码
		PageHelper.startPage(page, rows);
		// 执行查询
		List<TbItem> list = itemMapper.selectByExample(example);

		// 封装结果集
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		// 获取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult addItem(TbItem item, String desc, String itemParams) {
		// 生成商品id
		Long itemId = IDUtils.genItemId();
		// 封装商品信息
		Date date = new Date();
		item.setId(itemId);
		item.setStatus((byte) 1);
		item.setCreated(date);
		item.setUpdated(date);
		// 新增商品
		itemMapper.insert(item);

		// 新增商品描述
		TaotaoResult result = insertItemDesc(itemId, desc);
		if (result.getStatus() != 200) {
			throw new RuntimeException();
		}
		// 新增商品规格参数
		result = insertItemParam(itemId, itemParams);
		if (result.getStatus() != 200) {
			throw new RuntimeException();
		}

		// 成功
		return TaotaoResult.ok();
	}

	/**
	 * 新增商品描述信息
	 * 
	 * @param itemId
	 * @param desc
	 * @return
	 */
	private TaotaoResult insertItemDesc(long itemId, String desc) {
		// 封装商品描述信息
		Date date = new Date();
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		// 新增商品描述信息
		itemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}

	/**
	 * 添加商品规格参数
	 * 
	 * @param itemId    商品id
	 * @param itemParam 规格参数
	 * @return
	 */
	private TaotaoResult insertItemParam(long itemId, String itemParam) {
		// 封装pojo
		Date date = new Date();
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(date);
		itemParamItem.setUpdated(date);
		// 执行插入
		itemParamItemMapper.insert(itemParamItem);
		return TaotaoResult.ok();
	}

}
