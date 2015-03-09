package com.csl.util.date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;

public class TrunkBranchDate {
	private static String[] TERMDATA={"9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e","97bcf97c3598082c95f8c965cc920f",
		"97bd0b06bdb0722c965ce1cfcc920f","b027097bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e",
		"97bcf97c359801ec95f8c965cc920f","97bd0b06bdb0722c965ce1cfcc920f","b027097bd097c36b0b6fc9274c91aa",
		"97b6b97bd19801ec9210c965cc920e","97bcf97c359801ec95f8c965cc920f",	"97bd0b06bdb0722c965ce1cfcc920f",
		"b027097bd097c36b0b6fc9274c91aa","9778397bd19801ec9210c965cc920e","97b6b97bd19801ec95f8c965cc920f",
		"97bd09801d98082c95f8e1cfcc920f","97bd097bd097c36b0b6fc9210c8dc2","9778397bd197c36c9210c9274c91aa",
		"97b6b97bd19801ec95f8c965cc920e","97bd09801d98082c95f8e1cfcc920f",	"97bd097bd097c36b0b6fc9210c8dc2",
		"9778397bd097c36c9210c9274c91aa","97b6b97bd19801ec95f8c965cc920e","97bcf97c3598082c95f8e1cfcc920f",
		"97bd097bd097c36b0b6fc9210c8dc2","9778397bd097c36c9210c9274c91aa","97b6b97bd19801ec9210c965cc920e",
		"97bcf97c3598082c95f8c965cc920f","97bd097bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa",
		"97b6b97bd19801ec9210c965cc920e","97bcf97c3598082c95f8c965cc920f",	"97bd097bd097c35b0b6fc920fb0722",
		"9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e","97bcf97c359801ec95f8c965cc920f",
		"97bd097bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e",
		"97bcf97c359801ec95f8c965cc920f","97bd097bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa",
		"97b6b97bd19801ec9210c965cc920e","97bcf97c359801ec95f8c965cc920f",	"97bd097bd07f595b0b6fc920fb0722",
		"9778397bd097c36b0b6fc9210c8dc2","9778397bd19801ec9210c9274c920e","97b6b97bd19801ec95f8c965cc920f",
		"97bd07f5307f595b0b0bc920fb0722","7f0e397bd097c36b0b6fc9210c8dc2","9778397bd097c36c9210c9274c920e",
		"97b6b97bd19801ec95f8c965cc920f","97bd07f5307f595b0b0bc920fb0722","7f0e397bd097c36b0b6fc9210c8dc2",
		"9778397bd097c36c9210c9274c91aa","97b6b97bd19801ec9210c965cc920e","97bd07f1487f595b0b0bc920fb0722",
		"7f0e397bd097c36b0b6fc9210c8dc2","9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e",
		"97bcf7f1487f595b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722",	"9778397bd097c36b0b6fc9274c91aa",
		"97b6b97bd19801ec9210c965cc920e","97bcf7f1487f595b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722",
		"9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e","97bcf7f1487f531b0b0bb0b6fb0722",
		"7f0e397bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa","97b6b97bd19801ec9210c965cc920e",
		"97bcf7f1487f531b0b0bb0b6fb0722","7f0e397bd07f595b0b6fc920fb0722",	"9778397bd097c36b0b6fc9274c91aa",
		"97b6b97bd19801ec9210c9274c920e","97bcf7f0e47f531b0b0bb0b6fb0722","7f0e397bd07f595b0b0bc920fb0722",
		"9778397bd097c36b0b6fc9210c91aa","97b6b97bd197c36c9210c9274c920e","97bcf7f0e47f531b0b0bb0b6fb0722",
		"7f0e397bd07f595b0b0bc920fb0722","9778397bd097c36b0b6fc9210c8dc2","9778397bd097c36c9210c9274c920e",
		"97b6b7f0e47f531b0723b0b6fb0722","7f0e37f5307f595b0b0bc920fb0722",	"7f0e397bd097c36b0b6fc9210c8dc2",
		"9778397bd097c36b0b70c9274c91aa","97b6b7f0e47f531b0723b0b6fb0721","7f0e37f1487f595b0b0bb0b6fb0722",
		"7f0e397bd097c35b0b6fc9210c8dc2","9778397bd097c36b0b6fc9274c91aa","97b6b7f0e47f531b0723b0b6fb0721",
		"7f0e27f1487f595b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722",	"9778397bd097c36b0b6fc9274c91aa",
		"97b6b7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722",
		"9778397bd097c36b0b6fc9274c91aa","97b6b7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722",
		"7f0e397bd097c35b0b6fc920fb0722","9778397bd097c36b0b6fc9274c91aa","97b6b7f0e47f531b0723b0b6fb0721",
		"7f0e27f1487f531b0b0bb0b6fb0722","7f0e397bd07f595b0b0bc920fb0722",	"9778397bd097c36b0b6fc9274c91aa",
		"97b6b7f0e47f531b0723b0787b0721","7f0e27f0e47f531b0b0bb0b6fb0722","7f0e397bd07f595b0b0bc920fb0722",
		"9778397bd097c36b0b6fc9210c91aa","97b6b7f0e47f149b0723b0787b0721","7f0e27f0e47f531b0723b0b6fb0722",
		"7f0e397bd07f595b0b0bc920fb0722","9778397bd097c36b0b6fc9210c8dc2","977837f0e37f149b0723b0787b0721",
		"7f07e7f0e47f531b0723b0b6fb0722","7f0e37f5307f595b0b0bc920fb0722","7f0e397bd097c35b0b6fc9210c8dc2",
		"977837f0e37f14998082b0787b0721","7f07e7f0e47f531b0723b0b6fb0721","7f0e37f1487f595b0b0bb0b6fb0722",
		"7f0e397bd097c35b0b6fc9210c8dc2","977837f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721",
		"7f0e27f1487f531b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722","977837f0e37f14998082b0787b06bd",
		"7f07e7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722","7f0e397bd097c35b0b6fc920fb0722",
		"977837f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722",
		"7f0e397bd07f595b0b0bc920fb0722","977837f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721",
		"7f0e27f1487f531b0b0bb0b6fb0722","7f0e397bd07f595b0b0bc920fb0722",	"977837f0e37f14998082b0787b06bd",
		"7f07e7f0e47f149b0723b0787b0721","7f0e27f0e47f531b0b0bb0b6fb0722","7f0e397bd07f595b0b0bc920fb0722",
		"977837f0e37f14998082b0723b06bd","7f07e7f0e37f149b0723b0787b0721","7f0e27f0e47f531b0723b0b6fb0722",
		"7f0e397bd07f595b0b0bc920fb0722","977837f0e37f14898082b0723b02d5","7ec967f0e37f14998082b0787b0721",
		"7f07e7f0e47f531b0723b0b6fb0722","7f0e37f1487f595b0b0bb0b6fb0722","7f0e37f0e37f14898082b0723b02d5",
		"7ec967f0e37f14998082b0787b0721","7f07e7f0e47f531b0723b0b6fb0722","7f0e37f1487f531b0b0bb0b6fb0722",
		"7f0e37f0e37f14898082b0723b02d5","7ec967f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721",
		"7f0e37f1487f531b0b0bb0b6fb0722","7f0e37f0e37f14898082b072297c35","7ec967f0e37f14998082b0787b06bd",
		"7f07e7f0e47f531b0723b0b6fb0721","7f0e27f1487f531b0b0bb0b6fb0722","7f0e37f0e37f14898082b072297c35",
		"7ec967f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721",	"7f0e27f1487f531b0b0bb0b6fb0722",
		"7f0e37f0e366aa89801eb072297c35","7ec967f0e37f14998082b0787b06bd","7f07e7f0e47f149b0723b0787b0721",
		"7f0e27f1487f531b0b0bb0b6fb0722","7f0e37f0e366aa89801eb072297c35","7ec967f0e37f14998082b0723b06bd",
		"7f07e7f0e47f149b0723b0787b0721","7f0e27f0e47f531b0723b0b6fb0722","7f0e37f0e366aa89801eb072297c35",
		"7ec967f0e37f14998082b0723b06bd","7f07e7f0e37f14998083b0787b0721","7f0e27f0e47f531b0723b0b6fb0722",
		"7f0e37f0e366aa89801eb072297c35","7ec967f0e37f14898082b0723b02d5","7f07e7f0e37f14998082b0787b0721",
		"7f07e7f0e47f531b0723b0b6fb0722","7f0e36665b66aa89801e9808297c35",	"665f67f0e37f14898082b0723b02d5",
		"7ec967f0e37f14998082b0787b0721","7f07e7f0e47f531b0723b0b6fb0722",	"7f0e36665b66a449801e9808297c35",
		"665f67f0e37f14898082b0723b02d5","7ec967f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721",
		"7f0e36665b66a449801e9808297c35","665f67f0e37f14898082b072297c35",	"7ec967f0e37f14998082b0787b06bd",
		"7f07e7f0e47f531b0723b0b6fb0721","7f0e26665b66a449801e9808297c35",	"665f67f0e37f1489801eb072297c35",
		"7ec967f0e37f14998082b0787b06bd","7f07e7f0e47f531b0723b0b6fb0721",	"7f0e27f1487f531b0b0bb0b6fb0722"
		};
	private static String[] TRUNKS = { "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛",
			"壬", "癸" };
	private static String[] BRANCHES = { "子", "丑", "寅", "卯", "辰", "巳", "午",
			"未", "申", "酉", "戌", "亥" };
	private int yearT;
	private int yearB;
	private int monthT;
	private int monthB;
	private int dayT;
	private int dayB;
	private int hourT;
	private int hourB;
	private static int[][] hourTB = {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2 },
			{ 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4 },
			{ 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6 },
			{ 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8 },
			{ 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 } };

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
		hourT = hourTB[dayT % 6][hourB];
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
