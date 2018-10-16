package com.taotao.rest.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * solrJ测试
 * 
 * @author kangyong
 * @date 2018年7月8日
 */
public class SolrJTest {

	/**
	 * 新增一条内容
	 * 
	 * @throws Exception
	 * @throws SolrServerException
	 */
	@Test
	public void addDocument() throws SolrServerException, Exception {
		// 创建连接
		SolrServer solrServer = new HttpSolrServer("http://111.231.87.104:8080/solr/");
		// 创建一个文档对象
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test001");
		document.addField("item_title", "测试商品");
		document.addField("item_price", 54321);
		// 把文档对象写入索引库
		solrServer.add(document);
		// 提交
		solrServer.commit();
	}

	/**
	 * 删除一条数据
	 * 
	 * @throws SolrServerException
	 * @throws IOException
	 */
	@Test
	public void delectDocument() throws SolrServerException, IOException {
		// 创建连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.10.128:8080/solr/");
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}

}
