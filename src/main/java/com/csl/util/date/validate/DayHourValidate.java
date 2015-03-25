package com.csl.util.date.validate;

import static com.csl.util.date.Constant.DAY_TRUNK_HOUR;

import com.csl.util.date.TrunkBranchDate;

public class DayHourValidate implements DateValidater<TrunkBranchDate> {

	@Override
	public int validate(TrunkBranchDate t) {
		if(DAY_TRUNK_HOUR[t.getDayT()]==t.getDayB())
			return 1;
		return 0;
	}
}
