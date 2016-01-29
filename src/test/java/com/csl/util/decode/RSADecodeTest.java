package com.csl.util.decode;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.DecimalFormat;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class RSADecodeTest {
	@Test
	public void testPubKey() throws Exception{
	}
	@Test
	public void testDF(){
		DecimalFormat df = new DecimalFormat("##0.00");
		System.out.println(df.format((2345l-987l)/(2345l*1d)*100));
	}
	@Test
	public void RSATest() throws Exception{
		HashMap<String,Object> keys = RSAUtils.getKeys();
		PublicKey pubkey = (PublicKey) keys.get("public");
		PrivateKey prikey = (PrivateKey) keys.get("private");
		String publickey = Base64.encodeBase64String(pubkey.getEncoded());
		String privateKey = Base64.encodeBase64String(prikey.getEncoded());
		System.out.println(publickey);
		System.out.println(privateKey);
		String data="xxxx1123";
		byte[] encode= EncodeUtils.encodeByRSAPublicKEY(data.getBytes(),publickey);
		byte[] decode = EncodeUtils.decodeByRSAPrivateKEY(encode, privateKey);
		System.out.println(Base64.encodeBase64String(encode));
		System.out.println(new String(decode));

	}

}
