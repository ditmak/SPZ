package com.csl.util.decode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.DecimalFormat;
import java.util.HashMap;

import lzma.sdk.lzma.Decoder;
import lzma.streams.LzmaInputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.csl.util.io.ByteIOUtils;

public class RSADecodeTest {
	@Test
	public void testPubKey() throws Exception {
	}

	@Test
	public void testDF() {
		DecimalFormat df = new DecimalFormat("##0.00");
		System.out.println(df.format((2345l - 987l) / (2345l * 1d) * 100));
	}

	@Test
	public void RSATest() throws Exception {
		HashMap<String, Object> keys = RSAUtils.getKeys();
		PublicKey pubkey = (PublicKey) keys.get("public");
		PrivateKey prikey = (PrivateKey) keys.get("private");
		String publickey = Base64.encodeBase64String(pubkey.getEncoded());
		String privateKey = Base64.encodeBase64String(prikey.getEncoded());
		System.out.println(publickey);
		System.out.println(privateKey);
		String data = "xxxx1123";
		byte[] encode = EncodeUtils.encodeByRSAPublicKEY(data.getBytes(),
				publickey);
		byte[] decode = EncodeUtils.decodeByRSAPrivateKEY(encode, privateKey);
		System.out.println(Base64.encodeBase64String(encode));
		System.out.println(new String(decode));

	}

	@Test
	public void TestPrivateKeyDecode() throws Exception {
		String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAICIcd2fsQYC0+sgeJDVHTCwe8z5l02iHruHP8fcEv9C9/eTpJUgzavSje5ZNjKbGlk0U7icqxdlBoHAHDSdDUzokOnvd5NxfI0Z2IcXLvTnGojAeZQvEcGCi47mtNd9W+Xz8puOdFz72cQUpSEga7l935a0LTOzOmBzLaJkiyQ/AgMBAAECgYAh0ENsU2uFsk6/533wVDBms7cN+IdmZAUDvpP35xyUTLDhT9UXalbRIgY2sv+AFq+60fpIcebMs8YfckO5T6vEtRbKDwm4DXIl9tP3MPUkxQYhKCrR6jGwzIyzXoGfnunSRDtIf+qZBNNfUEnz+ZpmwELQUyt0zXrjllIIVW24yQJBAMR+FMiARHPiySi0vh2a9OgyCkQGpHk8Oq1Hq7VEnrxaRqkONpq0axvANOTUTNxNkyv79/E2hPptEL0VkvrRWdMCQQCndYMtWqhJOF5/oBhPh7WpmyV+5jex3E/+oA1fx7TGJvLBE6GAK0EwWWex/K/siWijNH2EM/ZDFst0QxxS/fxlAkBqBBzykUJdxF8Et4BcON8q6NThHzRetUBzfdgHVZ7PwWWekSylC/M+ZdYkIOfXdYOSl8CPRPs6ot4FJz7GVW5BAkBnBG7Pbu8KJEhWISnzexwxm8UkXQI0Q8Fedh3kJ1o5cGp0htwamgF/efDF2Rps3Fdn13nD46eCvDorasG4+JDVAkEAutaCZV+j4T772B85TXmXxqnKFCTVZkuvuXGU+Rh+m3v1do1tUKFd9zCV6ZiAxo/7aKANaSDUQy9A53Ge2xEKbQ==";
		byte[] data = Base64
				.decodeBase64("UxJM9xbo0oviqzf74BH9OlmtmJ/34LGIMosddGNF+lTSfmNQ8ZS+aeRGFoCfG2IZSK2t+4RCx5DmOPSZBzx3QaNUpLGE2CVvGLFw3oDJAbyEinvkHLyUsN7baPwUO0WCrHSw9olihYbXyN8tTn1Aucg30XMOrYN5lm6NNBI0cwA=");
		byte[] result = EncodeUtils.decodeByRSAPrivateKEY(data, privateKey);
		System.out.println(new String(result));
	}
	@Test
	public void TestPublicKeyDecode() throws Exception{
		byte[] data = Base64
				.decodeBase64( "/Td6WFoAAATm1rRGAgAhARwAAAAQz1jM4AFZAQhdACkIBESiwUNn0quUUADIHgy25lSTQ8S7GVGqMSy0q5Ijn4uraIKNsr4VCbuZda9yeLIDQfXNG6QAiYmXZfWBlyJ4kcILaGrp2ZG4wSXw53hBr8sPwTQoPuB3lLkrHGVWcy8Vq2IQPigi54wwY4jITZbvnvl18SIako9k2eHFwxzIv4oPIOQTEYtSFtcpWCNgbKcYqjeSyTjpXZLKbq2rgzTu649Uxkg+btzz+Xz9qL84Xstd/+OKHMrwu4r8TGmXIzstwy2+8XkrWIoZFH5xl4LIZxMs1pA4ecmWWpJoJvl7twaBkgcIRbqnzXKxONRoF8zyoPNcBeB3hrV0tIzN+9C1p4XDqleAADryUS3iZ7HcQABpALaAgAA1ll1cbHEZ/sCAAAAAARZWg==");
		String hexData = Hex.encodeHexString(data);
		ByteArrayInputStream bais  = new ByteArrayInputStream(data);
		LzmaInputStream is = new LzmaInputStream(bais, new Decoder());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ByteIOUtils.copyData(is, baos);
		System.out.println(new String(baos.toByteArray()));
		System.out.println(hexData);
		System.out.println(DigestUtils.sha1Hex("Roman"));
		System.out.println(DigestUtils.sha256Hex("Roman"));
		System.out.println(DigestUtils.sha512Hex("Roman"));
	}

}
