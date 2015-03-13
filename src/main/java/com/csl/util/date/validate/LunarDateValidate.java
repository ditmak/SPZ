package com.csl.util.date.validate;

import com.csl.util.date.LunarDate;

public class LunarDateValidate implements DateValidater<LunarDate>  {
	/**
	 * 初一十五不能通过验证
	 */
	@Override
	public int validate(LunarDate date) {
		if(date.getDay()==1||date.getDay()==15)
			return 1;
		return 0;
	}

}
