package com.csl.util.baidu;

public class FileItem {
	private String file_name;
	@Override
	public String toString() {
		return "FileItem [file_name=" + file_name + ", size=" + size + "]";
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	private Long size;
}
