package com.csl.util.date.validate;

import static com.csl.util.date.Constant.EARTH_MIND;
import static com.csl.util.date.Constant.SKY_MIND;

import com.csl.util.date.LunarDate;
import com.csl.util.date.TrunkBranchDate;

public class GoblinValidate implements DateValidater {

	@Override
	public int validate(TrunkBranchDate t, LunarDate ld) {
		int month = t.getMonthB();
		if(t.getDayT()==SKY_MIND[month]||t.getDayT()==EARTH_MIND[month])
			return 1;
		return 0;
	}

	@Override
	public String getMessAge() {
		return "天魂地魄";
	}

}
