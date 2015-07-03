package com.csl.util.net;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.csl.util.io.ByteIOUtils;

public class HTTPUtil {
	public static HttpURLConnection getConn(String url, String cookies,
			String method,String userAgent) throws IOException {
		URL temp = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) temp
				.openConnection();
		if (cookies != null) {
			connection.setRequestProperty("cookie", cookies);
		}
		connection.setRequestProperty("host", "xxxx");
		connection.setRequestProperty("referer", "xxxx");
		connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; rv:29.0) Gecko/20100101 Firefox/29.0");
		connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

		connection.setRequestProperty("accept-language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		connection.setRequestProperty("connection", "keep-alive");
		connection.setRequestProperty("pragma", "no-cache");
		connection.setRequestMethod(method);
		if (userAgent != null) {
                    connection.setRequestProperty("user-agent", userAgent);
            }
		return connection;
	}
	       public static HttpURLConnection getConn(String url, String cookies,
                       String method) throws IOException {
              return getConn(url, cookies, method, null);
       }


	public static String getURLContent(String url,String cookies,String method
			) {
		return  getURLContent(url,cookies,method,null);
	}

	public static String getURLContent(String url,String cookies,String method,
			 Map<String, String> values) {
	
		try {
			HttpURLConnection conn = sendInfo(url, cookies, method, values);
			InputStream ips = conn.getInputStream();
			byte[] buf = ByteIOUtils.getInputSreamBytes(ips);
			return new String(buf,"utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			//return getURLContent(url,cookies,method,values);
			throw new RuntimeException();
		}
	
	}
	 public static List<Element> getATagListByURL(String url) {
	        try {
	            SAXReader reader = new SAXReader();
	            reader.setEntityResolver(new EntityResolver() {

	                @Override
	                public InputSource resolveEntity(String publicId,
	                        String systemId) throws SAXException, IOException {
	                    return new InputSource(new StringBufferInputStream(""));

	                }
	            });
	            Document doc = reader.read(new URL(url));
	            List<Element> list = doc.selectNodes("//a");
	            return list;
	        } catch (Exception e) {
	            System.out.println(e + url);
	            return getATagListByURL(url);
	        }

	    }
	public static HttpURLConnection sendInfo(String url,String cookies,String method,
			 Map<String, String> values) 
	{
		try{
		HttpURLConnection conn = getConn(url, cookies, method);
		if (values != null) {
			StringBuffer sb = new StringBuffer();
			Set<String> keys = values.keySet();
			for (String key : keys) {
				sb.append(key).append("=").append(values.get(key))
						.append("&");
			}
			sb.deleteCharAt(sb.length() - 1);
			conn.setDoOutput(true);
			conn.getOutputStream().write(sb.toString().getBytes());
			conn.getOutputStream().flush();
			conn.getOutputStream().close();
		}
		conn.connect();
		return conn;}
		catch (Exception e) {
			e.printStackTrace();
			return sendInfo(url, cookies, method, values);
		}
	}
    public static String getURLContent(String url) {
        return getURLContent(url, null, "GET");
        
    }
	
	
}
