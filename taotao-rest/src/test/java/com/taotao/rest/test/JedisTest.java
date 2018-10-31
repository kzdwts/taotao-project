package com.taotao.rest.test;

import java.io.IOException;
import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * 测试redis连接
 * 
 * @author kangyong
 * @date 2018年7月8日
 */
public class JedisTest {

	/**
	 * 测试jedis单机版
	 */
	@Test
	public void testJedisSingle() {
		// 创建一个jedis对象
		Jedis jedis = new Jedis("192.168.10.131", 6379);
		//jedis.auth("zhigui");
		// 调用jedis的方法，方法名和redis命令一致
		jedis.set("name", "王丽丽");
		String name = jedis.get("name");
		System.out.println(name);
		// 关闭
		jedis.close();
	}

	/**
	 * 测试jedis连接池
	 */
	@Test
	public void testJedisPool() {
		// 创建连接池
		JedisPool jedisPool = new JedisPool("192.168.10.128", 6379);
		Jedis jedis = jedisPool.getResource();
		jedis.auth("zhigui");

		// 使用
		jedis.set("key1", "qianmengting");
		String key1 = jedis.get("key1");
		System.out.println(key1);

		jedis.close();
		jedisPool.close();
	}

	/**
	 * 测试jedis集群
	 * 
	 * @throws Exception
	 */
	@Test
	public void testJedisCluster() throws Exception {
		// 创建节点
		HashSet<HostAndPort> nodes = new HashSet<>();
//		nodes.add(new HostAndPort("192.168.10.128", 7001));
//		nodes.add(new HostAndPort("192.168.10.128", 7002));
//		nodes.add(new HostAndPort("192.168.10.128", 7003));
//		nodes.add(new HostAndPort("192.168.10.128", 7004));
//		nodes.add(new HostAndPort("192.168.10.128", 7005));
//		nodes.add(new HostAndPort("192.168.10.128", 7006));
		nodes.add(new HostAndPort("192.168.10.131", 7001));
		nodes.add(new HostAndPort("192.168.10.131", 7002));
		nodes.add(new HostAndPort("192.168.10.131", 7003));
		nodes.add(new HostAndPort("192.168.10.131", 7004));
		nodes.add(new HostAndPort("192.168.10.131", 7005));
		nodes.add(new HostAndPort("192.168.10.131", 7006));
		// 连接集群
		JedisCluster jedisCluster = new JedisCluster(nodes);

		jedisCluster.set("key1", "wangmengting");
		String key1 = jedisCluster.get("key1");
		System.out.println(key1);
		// 关闭
		jedisCluster.close();
	}

	/**
	 * redis整合spring单机版测试
	 */
	@Test
	public void testSpringJedisSingle() {
		// 创建容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-*.xml");
		// 获取jedis对象
		JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
		// 连接
		Jedis jedis = pool.getResource();
		// jedis.auth("zhigui");

		// 读写
		String key1 = jedis.get("key1");
		System.out.println(key1);

		jedis.set("key1", "87819731");
		key1 = jedis.get("key1");
		System.out.println(key1);

		// 关闭
		jedis.close();
		pool.close();
	}

	/**
	 * spring整合redis 集群版测试
	 * 
	 * @throws IOException
	 */
	@Test
	public void testSpringJedisCluster() throws IOException {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:/spring/applicationContext-*.xml");
		JedisCluster jedisCluster = (JedisCluster) applicationContext.getBean("redisClient");
		String string = jedisCluster.get("key1");
		System.out.println(string);
		jedisCluster.close();
		
	}

}
