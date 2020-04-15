package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;
import com.taotao.service.PictureService;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件(图片)上传控制层
 * 
 * @author kangyong
 * @date 2018年7月5日
 */
@Controller
public class PictureController {

	@Autowired
	private PictureService pictureService;

	/**
	 * 图片上传
	 * 
	 * @param uploadFile 要上传的文件流
	 * @return
	 */
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PictureResult upload(MultipartFile uploadFile, HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		return pictureService.uploadPicture(uploadFile);
	}
}
