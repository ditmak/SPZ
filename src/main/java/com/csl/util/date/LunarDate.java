package com.csl.util.date;

import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class LunarDate {
	private static final DateTime LUNARBEGIN = new DateTime(1900,1,31,0,0);
	/**
	 * 农历信息 ，从农历1900年一月三十一日起
	 */
	private static long[] lunarInfo = { 0x04bd8, 0x04ae0, 0x0a570, 0x054d5,
			0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2, 0x04ae0,
			0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2,
			0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40,
			0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566, 0x0d4a0,
			0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7,
			0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0,
			0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355,
			0x04da0, 0x0a5b0, 0x14573, 0x052b0, 0x0a9a8, 0x0e950, 0x06aa0,
			0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263,
			0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0,
			0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b6a0, 0x195a6, 0x095b0,
			0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46,
			0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50,
			0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960, 0x0d954,
			0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0,
			0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0,
			0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0, 0x0ad50,
			0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,
			0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6,
			0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0, 0x055b2, 0x049b0,
			0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0, 0x14b63,
			0x09370, 0x049f8, 0x04970, 0x064b0, 0x168a6, 0x0ea50, 0x06b20,
			0x1a6c4, 0x0aae0, 0x0a2e0, 0x0d2e3, 0x0c960, 0x0d557, 0x0d4a0,
			0x0da50, 0x05d55, 0x056a0, 0x0a6d0, 0x055d4, 0x052d0, 0x0a9b8,
			0x0a950, 0x0b4a0, 0x0b6a6, 0x0ad50, 0x055a0, 0x0aba4, 0x0a5b0,
			0x052b0, 0x0b273, 0x06930, 0x07337, 0x06aa0, 0x0ad50, 0x14b55,
			0x04b60, 0x0a570, 0x054e4, 0x0d160, 0x0e968, 0x0d520, 0x0daa0,
			0x16aa6, 0x056d0, 0x04ae0, 0x0a9d4, 0x0a2d0, 0x0d150, 0x0f252,
			0x0d520 };

	/**
	 * 计算指定一年的农历天数，默认每个月天数为29天，判断每个月的大小月的情况，加上闰月的情况
	 * @param y
	 * @return
	 * @author v-songlechen
	 */
	private static int yearDays(int y) {
        int i, sum = 348;
        for (i = 0x8000; i > 0x8; i >>= 1) {
            if ((lunarInfo[y - 1900] & i) != 0) sum += 1;
        }
        return (sum + leapDays(y));
    }
	
	/**
	 * 计算一年闰月的天数，0x0a9d4，后四位为4，即闰月为四月，前四位为0，代表是小月，29天
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
	  *  0x0a9d4 为例，最后四位是4，即闰月为四月，0代表没有闰月
	  * @param year
	  * @return
	  * @author v-songlechen
	  */
	 private static int leapMonth(int year) {
	        return (int) (lunarInfo[year - 1900] & 0xf);
	    }
	 /**
	  *农历y年m月的天数
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
	 	
	 public static String toLunarDate(DateTime time){
		 Days days= Days.daysBetween(LUNARBEGIN, time);
		 int offset = days.getDays();
		 int year =0;
		 int month=0;
		 StringBuilder sb = new StringBuilder();
		 for(int i =1900;i<2100;i++){
			int temp = yearDays(i);
			if(offset<=temp){
				year = i;
				sb.append(year+"年");
				break;
			}
			offset-=temp;
		 }
		 for(int i=1;i<=12;i++){
			 int temp = monthDays(year, i);
			 if(offset<=temp){
				 month =i;
				 break;
			 }
			 offset-=temp;
		 }
		 int leapMonth=leapMonth(year);
		 offset+=1;
		 if((leapMonth>0&&month>leapMonth)){
			 month-=1;
			 sb.append("闰"+month+"月"+offset+"日");
		 }else
		 if(month==0){
			 sb.append("闰12月"+offset+"日");
		 }else{
			 sb.append(month+"月"+offset+"日");
		 }
		 return sb.toString();
	 }

}
