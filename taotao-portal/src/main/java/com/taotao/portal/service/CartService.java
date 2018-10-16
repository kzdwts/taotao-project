package com.taotao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

/**
 * 购物车业务层接口
 * 
 * @author kangyong
 * @date 2018年7月13日
 */
public interface CartService {

	/**
	 * 新增购物项
	 * 
	 * @param itemId   商品id
	 * @param num      商品数量
	 * @param request
	 * @param response
	 * @return
	 */
	TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 取出购物车商品
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 删除购物项
	 * 
	 * @param itemId   商品id
	 * @param request
	 * @param response
	 * @return
	 */
	TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);

}
