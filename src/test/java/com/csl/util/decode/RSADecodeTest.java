package com.csl.util.decode;

import java.io.File;
import java.text.DecimalFormat;

import org.junit.Test;

public class RSADecodeTest {
	@Test
	public void testPubKey() throws Exception{
		File file = new File("D://pubkey");
		String data = RasDecode.decode(file, "2a38a4a9316c49e5a833517c45d31070","8613985ec49eb8f757ae6439e879bb2a");
		System.out.println(data);
	}
	@Test
	public void testDF(){
		DecimalFormat df = new DecimalFormat("##0.00");
		System.out.println(df.format((2345l-987l)/(2345l*1d)*100));
	}

}
