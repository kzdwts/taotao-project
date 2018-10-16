package com.taotao.portal.service;

import com.taotao.portal.pojo.Order;

/**
 * 订单业务接口层
 * 
 * @author kangyong
 * @date 2018年7月15日
 */
public interface OrderService {

	/**
	 * 创建订单
	 * 
	 * @param order 订单信息
	 * @return 订单号
	 */
	String createOrder(Order order);

}
