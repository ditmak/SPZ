package com.csl.util.date.validate;

import org.joda.time.DateTime;
import static com.csl.util.date.Constant.*;
import com.csl.util.date.TrunkBranchDate;

public class TermValidate implements DateValidater<DateTime> {

	/**
	 * 限定的节气及改节气的前一天验证失败
	 */
	

	@Override
	public int validate(DateTime t) {
		for (int term : TERMWARINGS) {
			//不能使用(term+1)/2 ,如立春下标为2，得出结果为1，导致错误
			if(t.getMonthOfYear()==(term+2)/2){
				int day = TrunkBranchDate.getTermDay(t.getYear(), term+1);
				if(t.getDayOfMonth()==day||t.getDayOfMonth()==(day-1)){
					return 1;
				}
			}
			if(t.getMonthOfYear()<=(term+2)/2){
				return 0;
			}
		}
		return 0;
	}
}
