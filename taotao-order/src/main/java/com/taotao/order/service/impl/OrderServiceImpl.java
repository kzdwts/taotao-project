package com.taotao.order.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.dao.JedisClient;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

/**
 * 订单业务实现层
 * 
 * @author kangyong
 * @date 2018年7月13日
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private TbOrderMapper orderMapper;
	@Autowired
	private TbOrderItemMapper orderItemMapper;
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${ORDER_GEN_KEY}")
	private String ORDER_GEN_KEY;
	
	@Value("${ORDER_INIT_ID}")
	private String ORDER_INIT_ID;
	
	@Value("${ORDER_DETAIL_GEN_KEY}")
	private String ORDER_DETAIL_GEN_KEY;

	@Override
	public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {
		// 新增订单
		// 获取订单号
		String orderGenKey = jedisClient.get(ORDER_GEN_KEY);
		if (StringUtils.isBlank(orderGenKey)) {
			// 初始订单号
			jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
		}
		long orderId = jedisClient.incr(ORDER_GEN_KEY);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String orderDate = simpleDateFormat.format(new Date());
		// 补全订单信息
		// 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
		order.setStatus(1);
		Date date = new Date();
		order.setOrderId(orderDate + orderId);
		order.setCreateTime(date);
		order.setUpdateTime(date);
		// 执行插入
		orderMapper.insert(order);

		// 新增订单项
		for (TbOrderItem tbOrderItem : itemList) {
			// 补全订单明细
			long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
			tbOrderItem.setId(orderDetailId + "");
			tbOrderItem.setOrderId(orderDate + orderId);
			// 向订单明细插入记录
			orderItemMapper.insert(tbOrderItem);
		}

		// 新增物流信息
		orderShipping.setOrderId(orderDate + orderId);
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		orderShippingMapper.insert(orderShipping);

		// 返回订单号
		return TaotaoResult.ok(orderDate + orderId);
	}

}
