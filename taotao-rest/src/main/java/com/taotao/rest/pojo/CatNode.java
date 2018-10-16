package com.taotao.rest.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 商品类别节点
 * 
 * @author kangyong
 * @date 2018年7月7日
 */
public class CatNode {

	@JsonProperty("n")
	private String name;
	@JsonProperty("u")
	private String url;
	@JsonProperty("i")
	private List<?> item;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<?> getItem() {
		return item;
	}

	public void setItem(List<?> item) {
		this.item = item;
	}

}
