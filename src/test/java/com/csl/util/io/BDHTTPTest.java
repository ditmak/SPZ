package com.csl.util.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.csl.util.baidu.BaiduFileFilter;
import com.csl.util.baidu.BaiduFileSizeFilter;
import com.csl.util.baidu.BaiduUtil;
import com.csl.util.decode.RSAUtils;

public class BDHTTPTest {
	public void test1() throws IOException, InterruptedException{
		String cookie ="PANWEB=1; bdshare_firstime=1387025521576; BAIDUID=C86B4898901218D2FA0DB23F946D0BBF:FG=1; pgv_pvi=3634836480; Hm_lvt_b181fb73f90936ebd334d457c848c8b5=1398403409,1398443764; BDUSS=lxQn5pQWtwOVo4cEh2UWNzdmtvVGpOfkZneExzTFMxUENlUkMxajVPbE9ib1JUQVFBQUFBJCQAAAAAAAAAAAEAAAD7hBoCY2hzbHpqAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAE7hXFNO4VxTdk; BDCLND=B13KaT6sc7s2%2BVz8p%2B0o%2BivWdl5yydFf; MCITY=-%3A; NBID=DFDA7E40D4404EBDC546F02DA4C86BF5:FG=1; PANPSC=14456482978422704385%3AYT8%2BiEEajGn%2B%2BMcCH3%2FABMxDaFs%2FKMru9ZU5tr1AoI3xTJ3v1z1m%2BmwSNU7PES55q62WUqlza1A%3D; Hm_lvt_773fea2ac036979ebb5fcc768d8beb67=1398858203,1398953355,1399132130,1399136560; Hm_lpvt_773fea2ac036979ebb5fcc768d8beb67=1399136560; Hm_lvt_adf736c22cd6bcc36a1d27e5af30949e=1398858204,1398953356,1399132130,1399136560; Hm_lpvt_adf736c22cd6bcc36a1d27e5af30949e=1399136560; cflag=65535%3A1";
		BufferedReader br = new BufferedReader(new FileReader("E:/a.txt"));
		String magnet =br.readLine();
		BaiduFileFilter filter = new BaiduFileSizeFilter();
		BaiduUtil util = new BaiduUtil(cookie, filter);
		int i =1;
		while(magnet!=null){
			System.out.println(magnet);
			String result = util.addTask(magnet, "/magnet/dl");
			System.out.println(result);
			System.out.println("success"+i);
			magnet = br.readLine();
			Thread.sleep(10000);
		}
		br.close();

	}
	@Test
	public void TestStringToByte() throws IOException{
		byte[] bytes = ByteIOUtils.StringToBytes("2a38a4a9316c49e5a833517c45d31070");
		String datea = ByteIOUtils.bytesToString(bytes);
		Assert.assertEquals("2a38a4a9316c49e5a833517c45d31070", datea.toLowerCase());
	}
	@Test
	public void test() throws Exception{
		// TODO Auto-generated method stub
		HashMap<String, Object> map = RSAUtils.getKeys();
		//生成公钥和私钥
		RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
		RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");

		//模
		String modulus = publicKey.getModulus().toString();
		//公钥指数
		String public_exponent = publicKey.getPublicExponent().toString();
		//私钥指数
		String private_exponent = privateKey.getPrivateExponent().toString();
		//明文
		String ming = "123456789";
		//使用模和指数生成公钥和私钥
		RSAPublicKey pubKey = RSAUtils.getPublicKey(modulus, public_exponent);
		RSAPrivateKey priKey = RSAUtils.getPrivateKey(modulus, private_exponent);
		//加密后的密文
		String mi = RSAUtils.encryptByPublicKey(ming, pubKey);
		System.err.println(mi);
		//解密后的明文
		ming = RSAUtils.decryptByPrivateKey(mi, priKey);
		System.err.println(ming);
	}

}
