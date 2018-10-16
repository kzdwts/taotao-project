package com.taotao.order.dao;

/**
 * jedis客户端
 * 
 * @author kangyong
 * @date 2018年7月8日
 */
public interface JedisClient {

	String get(String key);

	String set(String key, String value);

	String hget(String hkey, String key);

	long hset(String hkey, String key, String value);

	/**
	 * 自增，返回新值
	 * 
	 * @param key
	 * @return
	 */
	long incr(String key);

	/**
	 * 设置key的生命周期
	 * 
	 * @param key
	 * @param second 秒
	 * @return
	 */
	long expire(String key, int second);

	/**
	 * 查询key的生命周期
	 * 
	 * @param key 键
	 * @return
	 */
	long ttl(String key);

	/**
	 * 删除key
	 * 
	 * @param key
	 * @return
	 */
	long del(String key);

	long hdel(String hkey, String key);

}
