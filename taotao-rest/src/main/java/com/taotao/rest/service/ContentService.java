package com.taotao.rest.service;

import java.util.List;

import com.taotao.pojo.TbContent;

/**
 * 广告管理业务层接口
 * 
 * @author kangyong
 * @date 2018年7月7日
 */
public interface ContentService {

	/**
	 * 根据类别查询广告列表
	 * 
	 * @param contentCid 广告类别id
	 * @return
	 */
	List<TbContent> getContentList(long contentCid);

}
