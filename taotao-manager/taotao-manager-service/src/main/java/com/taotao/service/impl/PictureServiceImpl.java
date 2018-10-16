package com.taotao.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;

/**
 * 图片上传业务实现层
 * 
 * @author kangyong
 * @date 2018年7月5日
 */
@Service
public class PictureServiceImpl implements PictureService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USER_NAME}")
	private String FTP_USER_NAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;

	@Override
	public PictureResult uploadPicture(MultipartFile uploadFile) {
		// 判断上传图片是否为空
		if (null == uploadFile || uploadFile.isEmpty()) {
			return PictureResult.error("上传图片为空");
		}

		// 取文件扩展名
		String originalFileName = uploadFile.getOriginalFilename();
		String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
		// 生成新文件名(使用UUID生成)
		String imageName = IDUtils.genImageName();
		// 图片在服务器存放的位置(使用日期分割的目录结构)
		DateTime dateTime = new DateTime();
		String filePath = dateTime.toString("/yyyy/MM/dd");
		try {
			FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USER_NAME, FTP_PASSWORD, FTP_BASE_PATH, filePath, imageName + ext, uploadFile.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
			return PictureResult.error(ExceptionUtil.getStackTrace(e));
		}
		// 返回结果，生成一个可以访问到图片的url

		return PictureResult.ok(IMAGE_BASE_URL + filePath + "/" + imageName + ext);
	}

}
