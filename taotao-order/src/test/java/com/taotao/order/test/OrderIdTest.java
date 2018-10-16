package com.taotao.order.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderIdTest {
	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String orderDate = simpleDateFormat.format(new Date());
		System.out.println(orderDate);
	}
}
