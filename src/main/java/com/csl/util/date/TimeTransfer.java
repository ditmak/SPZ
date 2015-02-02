package com.csl.util.date;

import java.util.Calendar;

public class TimeTransfer {
	private static final String[] EARTH_BRANCH={"子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥"};
	public static String getEarchBranch(int index){
		index = (index+1==24)?0:index+1;
		return EARTH_BRANCH[index/2];
	}
	public static String getEarchBranch(Calendar cal){
		int index = cal.get(Calendar.HOUR_OF_DAY);
		return getEarchBranch(index);
	}
}
