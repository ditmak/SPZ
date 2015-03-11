package com.csl.util.date.validate;

import org.joda.time.DateTime;
import static com.csl.util.date.Constant.*;
import com.csl.util.date.TrunkBranchDate;

public class TermValidate implements DateValidater<DateTime> {



	@Override
	public int validate(DateTime t) {
		for (int term : TERMWARINGS) {
			if(t.getMonthOfYear()==(term+1)/2){
				int day = TrunkBranchDate.getTermDay(t.getYear(), term+1);
				if(t.getDayOfMonth()==day||t.getDayOfMonth()==(day-1)){
					return 1;
				}
			}
			if(t.getMonthOfYear()<=(term+1)/2){
				return 0;
			}
		}
		return 0;
	}
}
