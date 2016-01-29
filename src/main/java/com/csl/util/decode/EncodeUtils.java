package com.csl.util.decode;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class EncodeUtils{
	public static String RSA_CODE="RSA";
	private static byte[] doFinal(Cipher cipher,byte[] data,int size) throws Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int offset = 0;
		int inputLength = data.length;
		int avaliableSize = inputLength;
		while(avaliableSize>0){
			byte[] newData;
			if(avaliableSize>size){
				newData = cipher.doFinal(data,offset,size);
			}else{
				newData = cipher.doFinal(data,offset,avaliableSize);
			}
			baos.write(newData);
			avaliableSize-=size;
			offset+=size;
		}
		return baos.toByteArray();
	}
	public static byte[] encodeByKey(byte[] data,Key key,Cipher cipher) throws Exception{
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return doFinal(cipher, data, 117);
	}
	public static byte[] decodeByKey(byte[] data,Key key,Cipher cipher)throws Exception{
		cipher.init(Cipher.DECRYPT_MODE, key);
		return doFinal(cipher, data, 128);
	}
	public static byte[] encodeByRSAKEY(byte[] data,Key key) throws Exception{
		Cipher cipher =Cipher.getInstance("RSA");
		return encodeByKey(data, key, cipher);
	}
	public static byte[] decodeByRSAKEY(byte[] data,Key publickey) throws Exception{
		Cipher cipher = Cipher.getInstance("RSA");
		return decodeByKey(data, publickey, cipher);
	}
	public static byte[] encodeByRSAPublicKEY(byte[]data ,String key)throws Exception{
		return encodeByRSAKEY(data, string2RSAPublicKey(key));
	}
	public static byte[] encodeByRSAPrivateKEY(byte[]data ,String key)throws Exception{
		return encodeByRSAKEY(data, string2RSAPrivateKey(key));
	}
	public static byte[] decodeByRSAPublicKEY(byte[]data ,String key)throws Exception{
		return decodeByRSAKEY(data, string2RSAPublicKey(key));
	}
	public static byte[] decodeByRSAPrivateKEY(byte[]data ,String key)throws Exception{
		return decodeByRSAKEY(data, string2RSAPrivateKey(key));
	}
	public static Key string2RSAPublicKey(String key) throws Exception{
		X509EncodedKeySpec  spec = new X509EncodedKeySpec(Base64.decodeBase64(key));
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_CODE);
		return keyFactory.generatePublic(spec);
	}
	public static Key string2RSAPrivateKey(String key) throws Exception{
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.decodeBase64(key));
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_CODE);
		return keyFactory.generatePrivate(spec);
	}
}
