package com.csl.util.date.validate;

import com.csl.util.date.TrunkBranchDate;

public class TBYearMonthValidate implements DateValidater<TrunkBranchDate>{
	
	@Override
	public int validate(TrunkBranchDate t) {
		if(isTaiSui(t)||isYueJian(t))
			return 1;
		return 0;
	}
	private boolean isTaiSui(TrunkBranchDate date){
		if((date.getYearT()==date.getDayT())&&(date.getYearB()==date.getDayB()))
			return Boolean.TRUE;
		if((date.getYearT()==date.getHourT())&&(date.getYearB()==date.getHourB()))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
	private boolean isYueJian(TrunkBranchDate date){
		if((date.getMonthT()==date.getDayT())&&(date.getMonthB()==date.getDayB()))
			return Boolean.TRUE;
		if((date.getMonthT()==date.getHourT())&&(date.getMonthB()==date.getHourB()))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

}
