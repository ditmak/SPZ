package com.csl.util.decode;

import org.apache.commons.codec.digest.DigestUtils;


public class SecretUtils {
	public static void main(String[] args){
		String data = "Roman Hitman";
		String md5 = DigestUtils.md5Hex(data);
		System.out.println(md5);
		String sha1 = DigestUtils.sha1Hex(data);
		System.out.println(sha1);
	}
}
