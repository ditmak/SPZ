package com.csl.util.tencent;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.csl.util.io.ByteIOUtils;


public class QQ {
	private static String appid = "549000912";
	private static String urlLogin = "http://xui.ptlogin2.qq.com/cgi-bin/xlogin";
	private static String urlCheck = "http://check.ptlogin2.qq.com/check";
	private static String urlCap = "http://captcha.qq.com/cap_union_show";
	private static String urlImg = "http://captcha.qq.com/getimgbysig";
	private static String urlSubmit="http://ptlogin2.qq.com/login";
	private static String urlBlog = "http://user.qzone.qq.com/p/b1/cgi-bin/blognew/get_abs";
	private String qq;
	private String session;
	private String password;
	private String vcode;
	private String loginSign ;
	private String showVcode="0";
	private HttpClient client ;
	private CookieStore store =new  BasicCookieStore();
	static{
	}
	public QQ(String qq,String password) throws Exception{
		this.qq=qq;
		this.password=password;
		Properties props = ConfigUtils.loadProperties("qqlogin.properties");
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
		printCookie();
		check();
	}
	private void check() throws Exception{
		Properties props = ConfigUtils.loadProperties("qqcheck.properties");
		RequestBuilder requestBuilder = RequestBuilder.get(urlCheck);
		if (props != null) {
			for (String key : props.stringPropertyNames()) {
				requestBuilder.addParameter(key, props.getProperty(key));
			}
		}
		requestBuilder.addParameter("uin", qq);
		requestBuilder.addParameter("appid", appid);
		requestBuilder.addParameter("login_sig", getCookie("pt_login_sig"));
		requestBuilder.build();
		HttpUriRequest get = requestBuilder.build();
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		byte[] bytes = ByteIOUtils.getInputSreamBytes(entity.getContent());
		String result = new String(bytes,"UTF-8");
		Pattern pattern = Pattern.compile("\'(.*?)\'");
		Matcher matcher = pattern.matcher(result);
		List<String> list = new ArrayList<String>();
		while(matcher.find()){
			list.add(matcher.group());
		}
		String[] arrays = (String[]) list.toArray();
		vcode=arrays[1];
		if(!arrays[0].equals("0")){
			System.out.println("error");
			showVcode="1";
		}
		else{
			session=arrays[3];
			login();
		}
		printCookie();
	}
	private void login(){
		Properties props =ConfigUtils.loadProperties("qqloginSubmit.properties");
		RequestBuilder requestBuilder = RequestBuilder.get(urlCheck);
		if (props != null) {
			for (String key : props.stringPropertyNames()) {
				requestBuilder.addParameter(key, props.getProperty(key));
			}
		}
		requestBuilder.addParameter("aid", appid);
		requestBuilder.addParameter("login_sig", getCookie("pt_login_sig"));
		requestBuilder.addParameter("u", qq);
		requestBuilder.addParameter("verifycode", vcode);
		requestBuilder.addParameter("pt_verifysession_v1", session);
		requestBuilder.addParameter("pt_vcode_v1", showVcode);
		requestBuilder.addParameter("p","xxx");
	}
	private void printCookie(){
		if (store != null) {
			for (Cookie cookie : store.getCookies()) {
				System.out.println(cookie);
			}
		}
	}
	private String getSecret(){
		return null;
	}
	private String getCookie(String name){
		if (store != null) {
			for (Cookie cookie : store.getCookies()) {
				if(cookie.getName().equals(name)){
					return cookie.getValue();
				}
			}
		}
		return "";
	}
}
