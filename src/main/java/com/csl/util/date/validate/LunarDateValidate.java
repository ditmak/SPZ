package com.csl.util.date.validate;

import com.csl.util.date.LunarDate;
import com.csl.util.date.TrunkBranchDate;

public class LunarDateValidate implements DateValidater  {
	/**
	 * 初一十五不能通过验证
	 */


	@Override
	public int validate(TrunkBranchDate tbd, LunarDate ld) {
		if(ld.getDay()==1||ld.getDay()==15)
			return 1;
		return 0;
	}

	@Override
	public String getMessAge() {
		return "农历初一十五";
	}

}
