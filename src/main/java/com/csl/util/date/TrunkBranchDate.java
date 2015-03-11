package com.csl.util.date;

import static com.csl.util.date.Constant.*;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;


public class TrunkBranchDate {
	public int getYearT() {
		return yearT;
	}
	public void setYearT(int yearT) {
		this.yearT = yearT;
	}
	public int getYearB() {
		return yearB;
	}
	public void setYearB(int yearB) {
		this.yearB = yearB;
	}
	public int getMonthT() {
		return monthT;
	}
	public void setMonthT(int monthT) {
		this.monthT = monthT;
	}
	public int getMonthB() {
		return monthB;
	}
	public void setMonthB(int monthB) {
		this.monthB = monthB;
	}
	public int getDayT() {
		return dayT;
	}
	public void setDayT(int dayT) {
		this.dayT = dayT;
	}
	public int getDayB() {
		return dayB;
	}
	public void setDayB(int dayB) {
		this.dayB = dayB;
	}
	public int getHourT() {
		return hourT;
	}
	public void setHourT(int hourT) {
		this.hourT = hourT;
	}
	public int getHourB() {
		return hourB;
	}
	public void setHourB(int hourB) {
		this.hourB = hourB;
	}
	private int yearT;
	private int yearB;
	private int monthT;
	private int monthB;
	private int dayT;
	private int dayB;
	private int hourT;
	private int hourB;
	public String getTBYear(DateTime time) {
		int year = time.getYear();
		if(time.getMonthOfYear()<2||(time.getMonthOfYear()==2&&time.getDayOfMonth()<getTermDay(time.getYear(), 3)))
			year--;
		int offset = year - 4;
		yearT = offset % 10;
		yearB = offset % 12;
		return TRUNKS[yearT] + BRANCHES[yearB] + "年";
	};

	public String getTBDay(DateTime time) {
		int offset = Days.daysBetween(new DateTime("1900-2-20"), time)
				.getDays();
		if(time.getHourOfDay()==23)
			offset++;
		dayT = offset % 10;
		dayB = offset % 12;
		return TRUNKS[dayT] + BRANCHES[dayB] + "日";
	}

	public TrunkBranchDate(DateTime time) {
		getTBYear(time);
		getTBMonth(time);
		getTBDay(time);
		getTBHour(time);
	}

	public String getTBMonth(DateTime time) {
		int offset = Months.monthsBetween(new DateTime("1900-1-1"), time)
				.getMonths() + 13;
		if(time.getDayOfMonth()<getTermDay(time.getYear(), time.getMonthOfYear()*2-1))
			offset--;
		monthT = offset % 10;
		monthB = offset % 12;
		return TRUNKS[monthT] + BRANCHES[monthB] + "月";
	}

	public String getTBHour(DateTime time) {
		hourB = getBranch(time.getHourOfDay());
		hourT = HOURTB[dayT % 6][hourB];
		return TRUNKS[hourT] + BRANCHES[hourB] + "时";
	}

	public int getBranch(int index) {
		index = (index + 1 == 24) ? 0 : index + 1;
		return index / 2;
	}

	public TrunkBranchDate() {
		this(DateTime.now());
	}

	public String toString() {
		return TRUNKS[yearT] + BRANCHES[yearB] + "年" + TRUNKS[monthT]
				+ BRANCHES[monthB] + "月" + TRUNKS[dayT] + BRANCHES[dayB] + "日"
				+ TRUNKS[hourT] + BRANCHES[hourB] + "时";
	}
	public static int getTermDay(int year,int num){
		int offset = num/4;
		String subData = TERMDATA[year-1900].substring(offset, offset+5);
		Integer data = Integer.parseInt(subData, 16);
		int reminder = num%4;
		return Integer.parseInt(data.toString().substring(reminder/2+reminder,reminder%2==0?reminder+1:reminder+2));
	}
}
