package com.taotao.portal.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * httpclient测试
 * 
 * @author kangyong
 * @date 2018年7月7日
 */
public class HttpClientTest {

	/**
	 * get无参
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void doGet() throws ClientProtocolException, IOException {
		// 创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个get对象
		HttpGet get = new HttpGet("https://www.taobao.com/");
		// 执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		// 取响应结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		HttpEntity entity = response.getEntity();
		String entityString = EntityUtils.toString(entity, "utf-8");
		System.out.println(entityString);
		// 关闭httpclient
		response.close();
		httpClient.close();
	}

	/**
	 * get带参数
	 * 
	 * @throws Exception
	 */
	@Test
	public void doGetWithParam() throws Exception {
		// 创建一个httpclient请求
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个uri对象
		URIBuilder uriBuilder = new URIBuilder("https://www.sogou.com/web");
		uriBuilder.addParameter("query", "分布式");
		HttpGet get = new HttpGet(uriBuilder.build());
		// 执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		// 取响应结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("statuscode=" + statusCode);
		HttpEntity entity = response.getEntity();
		String entityString = EntityUtils.toString(entity, "utf-8");
		System.out.println("entityString=" + entityString);
		// 关闭httpclient
		response.close();
		httpClient.close();
	}

	/**
	 * post无参
	 * 
	 * @throws IOException
	 */
	@Test
	public void doPost() throws IOException {
		// 创建httpclient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建post
		HttpPost post = new HttpPost("https://www.sogou.com/");
		// 执行post请求
		CloseableHttpResponse response = httpClient.execute(post);
		// 取响应结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("statuscode=" + statusCode);
		HttpEntity entity = response.getEntity();
		String entityString = EntityUtils.toString(entity, "utf-8");
		System.out.println("entityString=" + entityString);
		// 关闭httpclient
		response.close();
		httpClient.close();
	}

	/**
	 * post带参
	 * 
	 * @throws IOException
	 */
	@Test
	public void doPostWithParam() throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://www.sogou.com/web");
		// 创建一个entity。模拟一个表单
		List<NameValuePair> kvList = new ArrayList<>();
		kvList.add(new BasicNameValuePair("query", "王丽丽"));
		// 包装成一个Entity
		StringEntity entity = new UrlEncodedFormEntity(kvList, "utf-8");
		// 设置请求的内容
		post.setEntity(entity);

		// 执行请求
		CloseableHttpResponse response = httpClient.execute(post);
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("statuscode=" + statusCode);
		HttpEntity entityNew = response.getEntity();
		String entityString = EntityUtils.toString(entityNew, "utf-8");
		System.out.println("entityString=" + entityString);
		// 关闭httpclient
		response.close();
		httpClient.close();

	}

}
