package com.csl.util.io;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public final class ByteIOUtils {
	private ByteIOUtils() {
	};
	public static byte[] getInputSreamBytes( final InputStream  ips)throws IOException{
		final	ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len;
		while((len=ips.read(buf))!=-1)
		{
			baos.write(buf, 0, len);
		}
		byte[] data = baos.toByteArray();
		baos.close();
		return data;
	}
	public static void copyData(InputStream is,OutputStream os)throws IOException{
		byte[] buf = new byte[1024];
		int len;
		while((len=is.read(buf))!=-1)
		{
			os.write(buf, 0, len);
		}
	}
	public static List<String> getStrFromReader(Reader in) throws IOException{
		BufferedReader br = new BufferedReader(in);
		String str = "";
		List<String> results = new ArrayList<String>();
		while((str = br.readLine())!=null){
			if(str.length()>0) {
				results.add(str);
			}
		}
		return results;
	}
	public static List<String> getStrFromFile(File file) throws IOException{
		if(file.exists()){
			List<String> strs = null;
			Reader  reader = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			strs = ByteIOUtils.getStrFromReader(reader);
			reader.close();
			return strs;
		} else {
			return new ArrayList<String>(0);
		}
	}
	public static String bytesToString(byte[] data){
		StringBuilder sb = new StringBuilder();
		if (data != null) {
			for (byte b : data) {
				if(b>=10){
					char c= (char) ('A'+b%10);
					sb.append(c);
				}else{
					sb.append(b);
				}
			}
		}
		return sb.toString();
	}
	public static byte[] StringToBytes(String data) throws IOException{
		data = data.toUpperCase();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for(int i = 0 ;i <data.length() ; i++){
			char c = data.charAt(i);
			if(c>='0'&&c<='9'){
				byte[] b = new byte[1];
				b[0] = (byte) (c-'0');
				baos.write(b);
			}else if(c>='A'&&c<='F'){
				byte[] b = new byte[1];
				b[0] = (byte) ((byte) (c-'A')+10);
				baos.write(b);
			}else{
				throw new IllegalArgumentException("error char in data : "+c);
			}
		}
		return baos.toByteArray();
	}

}
