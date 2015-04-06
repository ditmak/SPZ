package com.csl.util.date.validate;

import static com.csl.util.date.Constant.MONTH_HOUR;

import com.csl.util.date.LunarDate;
import com.csl.util.date.TrunkBranchDate;

public class MonthHourValidate implements DateValidater {



	@Override
	public int validate(TrunkBranchDate tbd, LunarDate ld) {
		if(MONTH_HOUR[tbd.getMonthB()]==tbd.getDayB())
			return 1;
		return 0;
	}

	@Override
	public String getMessAge() {
		return "月忌时";
	}

}
