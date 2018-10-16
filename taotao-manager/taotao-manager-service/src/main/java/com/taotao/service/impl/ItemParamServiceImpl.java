package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;

/**
 * 商品规格参数模板业务实现层
 * 
 * @author kangyong
 * @date 2018年7月7日
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		// 封装查询条件
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		// 执行查询
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);

		// 是否查询到数据
		if (list != null && list.size() > 0) {
			return TaotaoResult.ok(list.get(0));
		}

		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemParam(Long cid, String paramData) {
		// 封装pojo
		Date date = new Date();
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		itemParam.setCreated(date);
		itemParam.setUpdated(date);
		// 插入到数据库
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

}
