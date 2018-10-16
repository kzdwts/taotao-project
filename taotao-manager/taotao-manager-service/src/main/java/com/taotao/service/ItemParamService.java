package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 商品规格参数业务层接口
 * 
 * @author kangyong
 * @date 2018年7月7日
 */
public interface ItemParamService {

	/**
	 * 商品规格模板详情查询
	 * 
	 * @param cid 类别id
	 * @return
	 */
	TaotaoResult getItemParamByCid(long cid);

	/**
	 * 新增商品规格参数模板
	 * 
	 * @param cid       类别id
	 * @param paramData 规格参数模板
	 * @return
	 */
	TaotaoResult insertItemParam(Long cid, String paramData);
	
}
