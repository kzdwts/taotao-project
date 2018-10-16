package com.taotao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

/**
 * 测试分页
 * 
 * @author kangyong
 * @date 2018年7月5日
 */
public class TestPageHelper {

	/**
	 * 分页测试
	 */
	@Test
	public void testPageHelper() {
		// 创建一个spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-*.xml");
		// 从spring容器中获取mapper的代理对象
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);

		// 查询条件
		TbItemExample example = new TbItemExample();
		// 分页
		PageHelper.startPage(1, 20);
		// 执行查询
		List<TbItem> list = itemMapper.selectByExample(example);

		// 遍历
		for (TbItem tbItem : list) {
			System.out.println("itemId=" + tbItem.getId() + "&name=" + tbItem.getTitle());
		}
		// 取分页信息
		PageInfo pageInfo = new PageInfo<>(list);
		System.out.println(pageInfo.getSize());
		System.out.println(pageInfo.getTotal());
	}
}
