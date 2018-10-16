package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

/**
 * 商品基本信息
 * 
 * @author kangyong
 * @date 2018年7月11日
 */
public class ItemInfo extends TbItem {

	public String[] getImages() {
		String image = getImage();
		if (image != null) {
			String[] images = image.split(",");
			return images;
		}
		return null;
	}

}
