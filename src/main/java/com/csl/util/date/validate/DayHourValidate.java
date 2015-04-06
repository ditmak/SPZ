package com.csl.util.date.validate;

import static com.csl.util.date.Constant.DAY_TRUNK_HOUR;

import com.csl.util.date.LunarDate;
import com.csl.util.date.TrunkBranchDate;

public class DayHourValidate implements DateValidater {


	@Override
	public int validate(TrunkBranchDate tbd, LunarDate ld) {
		if(DAY_TRUNK_HOUR[tbd.getDayT()]==tbd.getDayB())
			return 1;
		return 0;
	}

	@Override
	public String getMessAge() {
		return "日忌时";
	}
}
