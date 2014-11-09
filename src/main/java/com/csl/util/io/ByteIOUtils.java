package com.csl.util.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
}
