package com.csl.util.baidu;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.RuntimeErrorException;

import com.google.gson.Gson;

public class BaiduUtil {
	private static final String addTask = "add_task";
	private static final String query_magnetinfo = "query_magnetinfo";
	private String  url= "http://pan.baidu.com/rest/2.0/services/cloud_dl?bdstoken&channel=chunlei&clienttype=0&web=1";
	private static final String homePage = "http://pan.baidu.com";
	private static final Pattern BDstokenpattern = Pattern.compile("FileUtils\\.bdstoken=\"(.*?)\"");
	private static final Gson gson = new Gson();
	private String cookie;
	private String bdstoken;
	private BaiduFileFilter filter ;
	public BaiduUtil(String cookie,BaiduFileFilter filter) {
		super();
		this.cookie = cookie;
		this.bdstoken = getBDstoken();
		this.filter = filter;
		url= url.replace("bdstoken", "bdstoken="+bdstoken);
	}
	public  String addTask(String magnet,String savePath){
		MagnetDLReturn dlReturn = getMagnetInfo(magnet);
		return dlFile( dlReturn, savePath, magnet);
	}
	private  String getBDstoken(){
		String content = BaiduHTTPUtil.getURLContent(homePage, cookie, "GET");
		Matcher matcher = BDstokenpattern.matcher(content);
		if(matcher.find()){
			return matcher.group(1);
		}
		else 
			throw new RuntimeException("找不到bdstoken");
	};
	private  MagnetDLReturn getMagnetInfo(String magnet){
		Map<String,String> values = new HashMap<String, String>();
		values.put("app_id", "250528");
		values.put("method", query_magnetinfo);
		values.put("save_path", "/");
		values.put("source_url", magnet);
		values.put("type", "1");
		String returnData=BaiduHTTPUtil.getURLContent(url, cookie, "POST",values);
		 return gson.fromJson(returnData, MagnetDLReturn.class);
	}
	private  String dlFile(MagnetDLReturn dl,String savePath,String magnet){
		String index = filter.getFileIndex(dl);
		Map<String,String> values = new HashMap<String, String>();
		values.put("app_id", "250528");
		values.put("method", addTask);
		values.put("save_path", savePath);
		values.put("source_url", magnet);
		values.put("type", "4");
		values.put("t", System.currentTimeMillis()+"");
		values.put("selected_idx", index);
		values.put("task_from", "0");
		return BaiduHTTPUtil.getURLContent(url, cookie, "POST", values);
	};
	
}
