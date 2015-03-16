package com.csl.util.date;

import org.joda.time.DateTime;

public class WorkTimeDate {

	public static DateTime plusWorkDays(DateTime time,int days) throws Exception{
		int weeks = days/5;
		int day = days%5;
		DateTime result = time.plusWeeks(weeks);
		while(result.getDayOfWeek()>5)
			result = result.plusDays(1);
		result = result.plusDays(day);
		while(result.getDayOfWeek()>5)
			result = result.plusDays(1);
		return result;
	}

}
