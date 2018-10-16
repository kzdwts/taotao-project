package com.taotao.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

/**
 * 测试ftp上传文件
 * 
 * @author kangyong
 * @date 2018年7月5日
 */
public class FtpClientTest {

	/**
	 * ftp上传文件
	 * 
	 * @throws IOException
	 * @throws SocketException
	 */
	@Test
	public void testFtp() throws SocketException, IOException {
		// 创建ftp客户端
		FTPClient ftpClient = new FTPClient();
		// 创建连接，登陆到ftp
		ftpClient.connect("192.168.10.128", 21);
		ftpClient.login("ftpuser", "ftpuser");
		// 读取本地文件→流
		FileInputStream inputStream = new FileInputStream(new File("E:\\4.jpg"));

		// 上传文件
		// 指定上传目录
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		// 指定上传文件类型
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		// 上传
		boolean storeFile = ftpClient.storeFile("hello.jpg", inputStream);
		System.out.println(storeFile);
		// 登出
		ftpClient.logout();

	}

}
