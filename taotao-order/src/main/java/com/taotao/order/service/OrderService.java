package com.taotao.order.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

/**
 * 订单业务层接口
 * 
 * @author kangyong
 * @date 2018年7月13日
 */
public interface OrderService {

	/**
	 * 创建订单
	 * 
	 * @param order         订单信息
	 * @param itemList      订单项
	 * @param orderShipping 物流信息
	 * @return
	 */
	TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
	
}
