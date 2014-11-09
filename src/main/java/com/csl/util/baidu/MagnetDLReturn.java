package com.csl.util.baidu;

import java.util.List;

public class MagnetDLReturn {
	private List<FileItem> magnet_info;
	private int total;
	private String request_id;
	public List<FileItem> getMagnet_info() {
		return magnet_info;
	}
	public void setMagnet_info(List<FileItem> magnet_info) {
		this.magnet_info = magnet_info;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	@Override
	public String toString() {
		return "MagnetDLReturn [magnet_info=" + magnet_info + ", total="
				+ total + ", request_id=" + request_id + "]";
	}
	
}
