package com.csl.util.date;

import java.util.Calendar;

public class TrunkBranchDate {
	private Trunk yearTrunk;
	private Branch yearBranch;
	private Trunk monthTrunk;
	private Branch monthBranch;
	private Trunk dayTrunk;
	private Branch dayBranch;
	private Trunk hourTrunk;
	private Branch hourBranch;
	public TrunkBranchDate(Calendar cal) {
	//1，先判断是否过了立春，没过就以-1计算年份
	
	//2，根据月份判断是否过了该月的第一个节气，没过就减一
	
	//3,根据年份的结果和月份的结果判断月份的地支
		
	//4,算出该日干支
		
	//5,根据日子的干支时间计算时辰	
	}

}
