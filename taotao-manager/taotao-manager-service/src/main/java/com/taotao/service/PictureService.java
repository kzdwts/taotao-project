package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;

/**
 * 图片上传业务层接口
 * 
 * @author kangyong
 * @date 2018年7月5日
 */
public interface PictureService {

	/**
	 * 图片上传
	 * 
	 * @param uploadFile 要上传的文件
	 * @return
	 */
	PictureResult uploadPicture(MultipartFile uploadFile);
}
