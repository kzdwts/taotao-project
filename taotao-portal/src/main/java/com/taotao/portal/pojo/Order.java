package com.taotao.portal.pojo;

import java.util.List;

import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

/**
 * 订单信息
 * 
 * @author kangyong
 * @date 2018年7月13日
 */
public class Order extends TbOrder {

	/* 订单项 */
	private List<TbOrderItem> orderItems;
	/* 订单物流信息 */
	private TbOrderShipping orderShipping;

	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}

	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}

}
