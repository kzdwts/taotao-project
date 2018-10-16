package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

/**
 * 购物车控制层
 * 
 * @author kangyong
 * @date 2018年7月13日
 */
@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	/**
	 * 新增商品到购物车
	 * 
	 * @param itemId   商品id
	 * @param num      商品数量
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/add/{itemId}")
	public String addCartItem(@PathVariable Long itemId, @RequestParam(value = "num", defaultValue = "1") Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		// 新增商品到购物车，返回
		TaotaoResult result = cartService.addCartItem(itemId, num, request, response);
		return "cartSuccess";
	}

	/**
	 * 显示购物车
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/cart")
	public String showCart(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CartItem> list = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", list);
		return "cart";
	}

	/**
	 * 删除购物项
	 * 
	 * @param itemId   商品id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
		cartService.deleteCartItem(itemId, request, response);
		return "redirect:/cart/cart.html";
	}

}
