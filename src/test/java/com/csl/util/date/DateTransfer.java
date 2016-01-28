package com.csl.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;

public class DateTransfer {
	@Test
	public void testDateTransfer(){
		for(int i = 0 ;i < 24 ;i++) {
			System.out.println(TimeTransfer.getEarchBranch(i));
		}
	}
	@Test
	public void testDateTransfer2(){
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < 24; i++) {
			System.out.println(TimeTransfer.getEarchBranch(cal));
			cal.add(Calendar.HOUR_OF_DAY, 1);
		}
	}
	@Test
	public void testDate() throws Exception{
		DateTime time = WorkTimeDate.plusWorkDays(DateTime.now(), 33);
		System.out.println(time);
	}
	@Test
	public void TestData() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmm");
		Date date = sdf.parse("1601271410");
		System.out.println(date);
		System.out.println(new Date());
	}


}
