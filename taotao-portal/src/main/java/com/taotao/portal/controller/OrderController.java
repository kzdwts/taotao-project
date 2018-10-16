package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.taotao.pojo.TbUser;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.OrderService;

/**
 * 订单控制层
 * 
 * @author kangyong
 * @date 2018年7月13日
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;

	/**
	 * 进入订单确认页面
	 * 
	 * @return
	 */
	@RequestMapping("/order-cart")
	public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 取购物车商品列表
		List<CartItem> list = cartService.getCartItemList(request, response);
		// 传递给页面
		model.addAttribute("cartList", list);
		return "order-cart";
	}

	/**
	 * 创建订单
	 * 
	 * @param order
	 * @param model
	 * @return
	 */
	@RequestMapping("/create")
	public String createOrder(Order order, RedirectAttributes model, HttpServletRequest request) {
		try {
			// 补全order用户信息
			TbUser user = (TbUser) request.getAttribute("user");
			order.setUserId(user.getId());
			order.setBuyerNick(user.getUsername());
			// 创建订单
			String orderId = orderService.createOrder(order);
			// 回显数据
			model.addAttribute("orderId", orderId);
			model.addAttribute("payment", order.getPayment());
			model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
//			model.addFlashAttribute("order", order);
//			model.addFlashAttribute("orderId", orderId);
			// 跳转页面
			return "redirect:/order/success.html";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/exception";
		}
	}

	/**
	 * 转发到订单提交成功页面
	 * 
	 * @param order
	 * @param orderId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/success")
	public String successOrder(@ModelAttribute("order") Order order, @ModelAttribute("orderId") String orderId,
			Model model, HttpServletRequest request) {
		// 回显数据
		model.addAttribute("orderId", orderId);
		model.addAttribute("payment", order.getPayment());
		model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
		return "success";
	}

}
