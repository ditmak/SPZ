package com.csl.util;

import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


/**
 * 字符串加密与解密
 *
 * @author Jieven
 * @date 2014-5-12
 */
public class EncryptUtil {

	public static final String MD5 = "MD5";
	public static final String SHA1 = "SHA-1";

	/**
	 * 字符单向加密算法
	 * @param str 加密
	 * @return
	 */
	private static String encrypt(String type, String str) {
		try {
			String result = "";
			MessageDigest md = MessageDigest.getInstance(type);
			byte[] bytes = md.digest(str.getBytes("UTF-8"));
			for (byte b : bytes) {
				String hex = Integer.toHexString(b & 0xFF).toUpperCase();
				result += (hex.length() == 1 ? "0" : "") + hex;
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * MD5加密
	 * @param str
	 * @return
	 */
	public static String getMd5(String str) {
		return encrypt(MD5, str);
	}

	/**
	 * SHA1加密
	 * @param str
	 * @return
	 */
	public static String getSha1(String str) {
		return encrypt(SHA1, str);
	}

	/**
	 * 获取双重伪装加密
	 * @return
	 */
	public static String getSM32(String str) {
		return getSha1(getMd5(str)).substring(0, 32);
	}

	private static final String MK = "11111111111111111111111111111111111111111111111111111111";
	// 指定DES加密解密所用的密钥
	private static Key key;

	/**
	 * 设置加密的校验码
	 * @Date:2012-10-16 下午04:25:13
	 * @ClassDescription:
	 */
	private static void setkey(String keyStr) {
		try {
			DESKeySpec objDesKeySpec = new DESKeySpec(keyStr.getBytes("UTF-8"));
			SecretKeyFactory objKeyFactory = SecretKeyFactory.getInstance("DES");
			key = objKeyFactory.generateSecret(objDesKeySpec);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// 对字符串进行DES加密，返回BASE64编码的加密字符串


	// 对BASE64编码的加密字符串进行解密，返回解密后的字符串


	public static void main(String[] args) {
		System.out.println(EncryptUtil.getSM32("admin"));
		System.out.println(Double.POSITIVE_INFINITY);
		System.out.println(Double.NEGATIVE_INFINITY);
		// 21232F297A57A5A743894A0E4A801FC3
		// D033E22AE348AEB5660FC2140AEC35850C4DA997
		// DE5CAC556F600BEC8E4425383CA7D8E8
	}
}
