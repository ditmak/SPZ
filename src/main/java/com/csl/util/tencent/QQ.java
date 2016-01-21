package com.csl.util.tencent;

import java.util.List;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class QQ {
	private static String appid = "549000912";
	private static String urlLogin = "http://xui.ptlogin2.qq.com/cgi-bin/xlogin";
	private static String urlCheck = "http://check.ptlogin2.qq.com/check";
	private static String urlCap = "http://captcha.qq.com/cap_union_show";
	private static String urlImg = "http://captcha.qq.com/getimgbysig";
	private static String urlSubmit="http://ptlogin2.qq.com/login";
	private static String urlBlog = "http://user.qzone.qq.com/p/b1/cgi-bin/blognew/get_abs";
	private String qq;
	private String password;
	private String vcode;
	private String loginSign ;
	private HttpClient client ;
	public QQ(String qq,String password) throws Exception{
		Properties props = ConfigUtils.loadProperties("qqlogin.properties");
		CookieStore store =new  BasicCookieStore();
		client = HttpClients.custom().setDefaultCookieStore(store).build();
		RequestBuilder requestBuilder = RequestBuilder.get(urlLogin);
		if (props != null) {
			for (String key : props.stringPropertyNames()) {
				requestBuilder.addParameter(key, props.getProperty(key));
			}
		}
		HttpUriRequest get = requestBuilder.build();
		HttpResponse response1 = client.execute(get);
		HttpEntity entity = response1.getEntity();
		System.out.println("Login form get: " + response1.getStatusLine());
		EntityUtils.consume(entity);
		List<Cookie> cookies = store.getCookies();
		if (cookies.isEmpty()) {
			System.out.println("None");
		} else {
			for (int i = 0; i < cookies.size(); i++) {
				System.out.println("- " + cookies.get(i).toString());
			}
		}
	}
}
