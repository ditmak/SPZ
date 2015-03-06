package com.csl.util.date;


public class TrunkBranchDate {
	private static String[] TRUNKS = {"甲","乙","丙","丁","戊","己","庚","辛","壬","癸"};
	private static String[] BRANCHES = {"子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥"};
	public static String getTBYear(int year){
		int offset = year-4;
		return TRUNKS[offset%10]+BRANCHES[offset%12]+"年";
	};

}
