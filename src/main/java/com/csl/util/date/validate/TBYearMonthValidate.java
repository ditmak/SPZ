package com.csl.util.date.validate;

import com.csl.util.date.LunarDate;
import com.csl.util.date.TrunkBranchDate;

public class TBYearMonthValidate implements DateValidater{
	
	/**
	 * 与年柱或月柱相同的日柱或时柱不能通过验证
	 */

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
	@Override
	public int validate(TrunkBranchDate tbd, LunarDate ld) {
		if(isTaiSui(tbd)||isYueJian(tbd))
			return 1;
		return 0;
	}
	@Override
	public String getMessAge() {
		return "太岁月建";
	}

}
