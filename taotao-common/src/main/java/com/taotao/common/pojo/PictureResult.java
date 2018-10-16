package com.taotao.common.pojo;
/**
 * 图片上传返回结果实体类
 * @author kangyong
 * @date 2018年7月5日
 */
public class PictureResult {

	private int error;
	private String url;
	private String message;
	
	
	
	public PictureResult() {
	}
	
	
	public PictureResult(int error, String url, String message) {
		this.error = error;
		this.url = url;
		this.message = message;
	}
	
	/**
	 * 成功
	 * @param url 图片地址
	 * @return
	 */
	public static PictureResult ok(String url) {
		return new PictureResult(0, url, null);
	}
	
	/**
	 * 失败
	 * @param message 失败原因
	 * @return
	 */
	public static PictureResult error(String message) {
		return new PictureResult(1, null, message);
	}
	
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
