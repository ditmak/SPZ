package com.csl.util.date.validate;

import static com.csl.util.date.Constant.MONTH_HOUR;

import com.csl.util.date.TrunkBranchDate;

public class MonthHourValidate implements DateValidater<TrunkBranchDate> {

	@Override
	public int validate(TrunkBranchDate t) {
		if(MONTH_HOUR[t.getMonthB()]==t.getDayB())
			return 1;
		return 0;
	}

}
