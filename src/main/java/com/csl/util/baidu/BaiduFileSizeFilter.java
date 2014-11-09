package com.csl.util.baidu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BaiduFileSizeFilter implements BaiduFileFilter {

	@Override
	public String getFileIndex(MagnetDLReturn dlReturn) {
		List<FileItem> fileItems = dlReturn.getMagnet_info();
		List<Integer> list = new ArrayList<Integer>();
		for (FileItem fileItem : fileItems) {
			if(fileItem.getSize()>41943040){
				int i = fileItems.indexOf(fileItem);
				list.add(i+1);	
			}
		}
		String index =list.toString();
		index = index.substring(1,index.length()-1);
		return index;
	}

}
