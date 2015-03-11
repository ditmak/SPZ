package com.csl.util.date;

import org.joda.time.DateTime;
import org.joda.time.Days;

import static com.csl.util.date.Constant.*;
public class LunarDate {
	private int year;
	private int month;
	private int day;
	private boolean isLeapMonth=Boolean.FALSE;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public boolean isLeapMonth() {
		return isLeapMonth;
	}
	public void setLeapMonth(boolean isLeapMonth) {
		this.isLeapMonth = isLeapMonth;
	}
	/**
	 * 计算指定一年的农历天数，默认每个月天数为29天，判断每个月的大小月的情况，加上闰月的情况
	 * 
	 * @param y
	 * @return
	 * @author v-songlechen
	 */
	public static int yearDays(int y) {
		int i, sum = 348;
		for (i = 0x8000; i > 0x8; i >>= 1) {
			if ((lunarInfo[y - 1900] & i) != 0)
				sum += 1;
		}
		return (sum + leapDays(y));
	}
	public static DateTime getLunarStartDay(int year){
		return new DateTime(year,lunarCHUYIMonthInfo[year-1900]>>6,lunarCHUYIMonthInfo[year-1900]&0x3f,0,0);
	}
	/**
	 * 计算一年闰月的天数，0x0a9d4，后四位为4，即闰月为四月，前四位为0，代表是小月，29天
	 * 
	 * @param y
	 * @return
	 * @author v-songlechen
	 */
	private static int leapDays(int y) {
		if (leapMonth(y) != 0) {
			if ((lunarInfo[y - 1900] & 0x10000) != 0)
				return 30;
			else
				return 29;
		} else
			return 0;
	}

	/**
	 * 0x0a9d4 为例，最后四位是4，即闰月为四月，0代表没有闰月
	 * 
	 * @param year
	 * @return
	 * @author v-songlechen
	 */
	private static int leapMonth(int year) {
		return (int) (lunarInfo[year - 1900] & 0xf);
	}

	/**
	 * 农历y年m月的天数
	 * 
	 * @param y
	 * @param m
	 * @return
	 * @author v-songlechen
	 */
	private static int monthDays(int y, int m) {
		if ((lunarInfo[y - 1900] & (0x10000 >> m)) == 0)
			return 29;
		else
			return 30;
	}
	public LunarDate(DateTime time) {
		DateTime beginTime = getLunarStartDay(time.getYear());
		year = time.getYear();
		if(time.isBefore(beginTime)){
			beginTime = getLunarStartDay(--year);
		}
		Days days = Days.daysBetween(beginTime, time);
		day = days.getDays();
		month = 0;
		for (int i = 1; i <= 12; i++) {
			int temp = monthDays(year, i);
			if (day <= temp) {
				month = i;
				break;
			}
			day -= temp;
		}
		int leapMonth = leapMonth(year);
		day += 1;
		if (month == 0) {
			isLeapMonth=Boolean.TRUE;
			month=12;
		} else if ((leapMonth > 0 && month > leapMonth)) {
			month -= 1;
			if (month != leapMonth) {
				day = day - leapDays(year) + monthDays(year, month);
			} else {
				isLeapMonth=Boolean.TRUE;
			}
		}
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(year).append("年");
		if(isLeapMonth)
			builder.append("闰");
		builder.append(month).append("月").append(day).append("日");
		return builder.toString();
	}
	public static String toLunarDate(DateTime time) {
		return new LunarDate(time).toString();
	}

}
