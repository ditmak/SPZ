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
		DateTime date = new DateTime(2015,3,6,23,0);
		String str = LunarDate.toLunarDate(date);
		System.out.println(str);
	}
	@Test
	public void test3(){
		DateTime date = new DateTime(1900,1,31,0,0);
		StringBuilder sb1 = new StringBuilder();
		for(int i = 0;i<200;i++){
			int day = LunarDate.yearDays(1900+i);
			date = date.plusDays(day);
			int data =(date.getMonthOfYear()<<6)+date.getDayOfMonth();
			sb1.append("0x"+Integer.toString(data, 16)+",");
		} 
		System.out.println(sb1.toString());
	}
	@Test
	public void test4(){
		//System.out.println(TrunkBranchDate.getTBYear(2015));
		System.out.println(new TrunkBranchDate().getTBMonth(DateTime.now()));
	};
	@Test
	public void test5(){
		//System.out.println(TrunkBranchDate.getTBDay(DateTime.now()));
		System.out.println(new TrunkBranchDate().toString());
	};
	@Test
	public void test6(){
		System.out.println("7ec967f0e37f14998082b0787b06bd".length());
		int data = Integer.parseInt("7ec96", 16);
		System.out.println(data);
	}
	@Test
	public void test7(){
		System.out.println(new TrunkBranchDate(new DateTime("2015-2-19")));
	}
	@Test
	public void test8(){
		DateTime time= LunarDate.getLunarStartDay(2015);
		System.out.println(time);
		System.out.println(Constant.lunarCHUYIMonthInfo.length);
	}
}