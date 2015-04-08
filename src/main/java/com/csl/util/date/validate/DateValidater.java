package com.csl.util.date.validate;

import com.csl.util.date.LunarDate;
import com.csl.util.date.TrunkBranchDate;

public interface DateValidater {
	int validate(TrunkBranchDate tbd,LunarDate ld);
	String getMessAge();
}
