package com.csl.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;

import org.junit.Test;

public class MonStickerTest {
	@Test
	public void test1() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Map<String,String> params = new HashMap<String, String>();
		params.put("app_key", "1088");
		params.put("name", "1088");
		params.put("method", "test.http.testMethod");
		SimpleDateFormat sdf=  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp=sdf.format(new Date());
		params.put("timestamp", timestamp);
		StringBuffer sb = new StringBuffer();
		sb.append("httx");
		List<String> list = new ArrayList<String>(params.keySet());
		Collections.sort(list);
		for(String key:list){
			sb.append(key);
			sb.append(params.get(key));
		}
		sb.append("httx");
		MessageDigest md = MessageDigest.getInstance("MD5");
		String	result = byte2hex(md.digest(sb.toString().getBytes("utf-8")));
		System.out.println(result);
		sb= new StringBuffer();
		for(String key:list){
			sb.append(key).append("=").append(params.get(key)).append("&");
		}
		sb.append("sign=").append(result);
		System.out.println(sb.toString());
	}
	@Test
	public void UUIDTets(){
		for(int i =0;i<100;i++){
			String uuid = UUID.randomUUID().toString();
			System.out.println(uuid.substring(0, 6));}
	}
	private static String byte2hex(byte[] b) {

		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (byte element : b) {
			stmp = java.lang.Integer.toHexString(element & 0XFF);
			if (stmp.length() == 1) {
				hs.append("0").append(stmp);
			} else {
				hs.append(stmp);
			}
		}
		return hs.toString().toUpperCase();
	}
	@Test
	public void Datetest(){
		System.out.println(new Date(1443628800000l));
	}
	@Test
	public void httpTest(){
		Executors.newFixedThreadPool(8);
	}

}
