package com.taotao.common.pojo;

import java.util.List;

/**
 * easyUI支持的数据格式实体类
 * 
 * @author kangyong
 * @date 2018年7月5日
 */
public class EUDataGridResult {
	private long total;

	private List<?> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
