package com.taotao.search.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * solr-cloud测试
 * 
 * @author kangyong
 * @date 2018年7月16日
 */
public class SolrCloudTest {

	/**
	 * 添加
	 * 
	 * @throws SolrServerException
	 * @throws IOException
	 */
	@Test
	public void testAddDocument() throws SolrServerException, IOException {
		// 创建一个和solr集群的连接
		// 参数就是zookeeper的地址列表，使用逗号分隔
		String zkHost = "111.231.87.104:2181,111.231.87.104:2182,111.231.87.104:2183";
		CloudSolrServer solrServer = new CloudSolrServer(zkHost);
		// 设置默认的collection
		solrServer.setDefaultCollection("collection2");

		// 创建文档对象
		SolrInputDocument document = new SolrInputDocument();
		// 向文档中添加域
		document.addField("id", "test02");
		document.addField("item_title", "测试商品lll");
		// 把文档添加到索引库
		solrServer.add(document);

		// 提交
		solrServer.commit();
	}

	/**
	 * 删除
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	@Test
	public void testDeleteDocument() throws SolrServerException, IOException {
		// 创建一个和solr集群的连接
		// 参数就是zookeeper的地址列表，使用逗号分隔
		String zkHost = "111.231.87.104:2181,111.231.87.104:2182,111.231.87.104:2183";
		CloudSolrServer solrServer = new CloudSolrServer(zkHost);
		
		// 设置默认collection
		solrServer.setDefaultCollection("collection2");
		
		solrServer.deleteByQuery("*:*");
		// 提交
		solrServer.commit();
		
	}

}
