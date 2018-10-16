package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

/**
 * 购物车业务实现层
 * 
 * @author kangyong
 * @date 2018年7月13日
 */
@Service
public class CartServiceImpl implements CartService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITME_INFO_URL}")
	private String ITME_INFO_URL;

	@Override
	public TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
		// 商品信息
		CartItem cartItem = null;
		// 取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 遍历购物车
		for (CartItem cItem : itemList) {
			// 查看购物车是否已有此商品
			if (cItem.getId() == itemId) {
				// 已经有此商品
				cItem.setNum(cItem.getNum() + num);
				cartItem = cItem;
				break;
			}
		}

		// 购物车没有此商品
		if (cartItem == null) {
			// 创建新的购物项
			cartItem = new CartItem();
			// 查询商品信息
			try {
				String itemJson = HttpClientUtil.doGet(REST_BASE_URL + ITME_INFO_URL + itemId);
				// 转成java对象
				TaotaoResult taotaoResult = TaotaoResult.formatToPojo(itemJson, TbItem.class);
				if (taotaoResult.getStatus() == 200) {
					TbItem tbItem = (TbItem) taotaoResult.getData();
					cartItem.setId(tbItem.getId());
					cartItem.setNum(num);
					cartItem.setPrice(tbItem.getPrice());
					cartItem.setTitle(tbItem.getTitle());
					cartItem.setImage(tbItem.getImage() == null ? "" : tbItem.getImage().split(",")[0]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 追加到购物车
			itemList.add(cartItem);
		}
		// 购物车信息写入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);

		return TaotaoResult.ok();
	}

	/**
	 * 从cookie中取商品信息
	 * 
	 * @param request
	 * @return
	 */
	private List<CartItem> getCartItemList(HttpServletRequest request) {
		// 从cookie中取商品列表
		String cartJson = CookieUtils.getCookieValue(request, "TT_CART", true);
		if (cartJson == null) {
			// 如果购物车没有数据
			return new ArrayList<>();
		}
		// 如果有购物项，就取出返回
		try {
			List<CartItem> list = JsonUtils.jsonToList(cartJson, CartItem.class);
			System.out.println("try执行了");
			return list;
		} catch (Exception e) {
			System.out.println("catch执行了");
			e.printStackTrace();
		}
		System.out.println("最后返回了");
		return new ArrayList<>();
	}

	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		// 取出购物车商品（方法重载，直接调用）
		return getCartItemList(request);
	}

	@Override
	public TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
		// 从购物车取商品列表
		List<CartItem> list = getCartItemList(request);
		// 从列表中找到该商品
		for (CartItem cartItem : list) {
			if (cartItem.getId() == itemId) {
				list.remove(cartItem);
				// 退出循环
				break;
			}
		}

		// 购物车重新装入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(list), true);
		return TaotaoResult.ok();
	}

}
