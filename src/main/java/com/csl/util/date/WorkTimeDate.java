package com.csl.util.date;

import org.joda.time.DateTime;

public class WorkTimeDate {

	public static DateTime plusWorkDays(DateTime time,int days) throws Exception{
		//1.先计算可以有几个整周的日子
		int weeks = days/5;
		//剩余的日子
		int day = days%5;
		DateTime result = time.plusWeeks(weeks);
		//如果几周过后是周末，调整到周一
		while(result.getDayOfWeek()>5)
			result = result.plusDays(1);
		while(day-->0){
			result = result.plus(1);
		}
		return result;
	}

}
