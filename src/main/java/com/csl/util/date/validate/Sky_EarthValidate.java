package com.csl.util.date.validate;
import static com.csl.util.date.Constant.*;

import com.csl.util.date.LunarDate;
import com.csl.util.date.TrunkBranchDate;

public class Sky_EarthValidate implements DateValidater{

	boolean goblin(TrunkBranchDate t){
		int month = t.getMonthB();
		if(t.getDayB()==BIG_GOBLIN[month]||t.getHourB()==BIG_GOBLIN[month])
			return true;
		if(t.getDayB()==LITTLE_GOBLIN[month]||t.getHourB()==LITTLE_GOBLIN[month])
			return true;
		return false;
	}
	boolean mind(TrunkBranchDate t){
		int month = t.getMonthB();
		if(t.getDayT()==SKY_MIND[month]||t.getDayT()==EARTH_MIND[month])
			return true;
		return false;
	}
	@Override
	public int validate(TrunkBranchDate t, LunarDate ld) {
		int month = t.getMonthB();
		if(t.getDayB()==BIG_GOBLIN[month]||t.getHourB()==BIG_GOBLIN[month])
			return 1;
		if(t.getDayB()==LITTLE_GOBLIN[month]||t.getHourB()==LITTLE_GOBLIN[month])
			return 1;
		return 0;
	}
	@Override
	public String getMessAge() {
		return "大小煞";
	}
	
	

}
