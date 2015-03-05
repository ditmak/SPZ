package com.csl.util.date;

import org.joda.time.DateTime;
import org.junit.Test;

public class TestLunarDate {
	@Test
	public void test1(){
		String str = LunarDate.toLunarDate(DateTime.now());
		System.out.println(str);
	}
	@Test
	public void test2(){
		DateTime date = new DateTime(2006,9,15,0,0);
		String str = LunarDate.toLunarDate(date);
		System.out.println(str);
	}
}
