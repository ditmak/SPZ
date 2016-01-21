package com.csl.util.decode;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;

import javax.crypto.Cipher;

import com.csl.util.io.ByteIOUtils;

public class RasDecode {
	public static String decode(File file,String data1,String data2) throws Exception{
		FileInputStream fis = new FileInputStream(file);
		byte[] keybite = ByteIOUtils.getInputSreamBytes(fis);
		byte[] data= decryptByRSA(keybite,data1,data2);
		fis.close();
		System.out.println(data);
		return new String(data,"UTF-8");
	}
	public static byte[] decryptByRSA(byte[] data,String val1,String val2) {
		try {
			RSAPrivateKeySpec priv_spec = new RSAPrivateKeySpec(new BigInteger(val1,16),new BigInteger(val2,16));
			KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privKey = mykeyFactory.generatePrivate(priv_spec);
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, privKey);
			return cipher.doFinal(data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
