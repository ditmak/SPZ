package com.csl.util.decode;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.DecimalFormat;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class RSADecodeTest {
    @Test
    public void testPubKey() throws Exception {
    }

    @Test
    public void testDF() {
	DecimalFormat df = new DecimalFormat("##0.00");
	System.out.println(df.format(((2345l - 987l) / (2345l * 1d)) * 100));
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

}
