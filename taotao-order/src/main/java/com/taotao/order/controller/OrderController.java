package com.taotao.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;

/**
 * 订单控制层
 * 
 * @author kangyong
 * @date 2018年7月13日
 */
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * 创建新订单
	 * 
	 * @param order
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createOrder(@RequestBody Order order) {
		try {
			// 创建订单
			TaotaoResult result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

}
