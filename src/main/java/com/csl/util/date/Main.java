package com.csl.util.date;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.csl.util.date.validate.DateValidater;
import com.csl.util.date.validate.DayHourValidate;
import com.csl.util.date.validate.GoblinValidate;
import com.csl.util.date.validate.LunarDateValidate;
import com.csl.util.date.validate.MonthHourValidate;
import com.csl.util.date.validate.Sky_EarthValidate;
import com.csl.util.date.validate.TBYearMonthValidate;
import com.csl.util.date.validate.TermValidate;

import static com.csl.util.date.Constant.*;

public class Main {
	public static void main(String[] args) {

		DateTime dt = DateTime.now();
		if (args != null && args.length > 0) {
			String data = args[0];
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			Date date;
			try {
				date = (Date) sf.parse(data);
				dt = new DateTime(date.getTime());
			} catch (ParseException e) {
				System.out.println("请输入正确格式的数据如20150406");
				System.exit(-1);
			}

		}
		System.out.println(dt.toString("yyyy-MM-dd HH：mm:ss"));
		LunarDate ld = new LunarDate(dt);
		TrunkBranchDate tbd = new TrunkBranchDate(dt);
		System.out.println(tbd.toString());
		System.out.println("农历:" + ld.toString());
		List<DateValidater> list = new ArrayList<DateValidater>();
		list.add(new DayHourValidate());
		list.add(new LunarDateValidate());
		list.add(new MonthHourValidate());
		list.add(new Sky_EarthValidate());
		list.add(new TBYearMonthValidate());
		list.add(new TermValidate());
		list.add(new GoblinValidate());
		StringBuilder sb = new StringBuilder();
		for (DateValidater dateValidater : list) {
			if (dateValidater.validate(tbd, ld) != 0) {
				sb.append(dateValidater.getMessAge());
				sb.append("\r\n");
			}
		}
		System.out.println("天魂：" + TRUNKS[SKY_MIND[tbd.getMonthB()]]);
		System.out.println("地魄：" + TRUNKS[EARTH_MIND[tbd.getMonthB()]]);
		System.out.println("月忌时：" + BRANCHES[MONTH_HOUR[tbd.getMonthB()]]);
		System.out.println("日忌时：" + BRANCHES[DAY_TRUNK_HOUR[tbd.getDayT()]]);
		System.out.println("大煞：" + BRANCHES[BIG_GOBLIN[tbd.getMonthB()]]);
		System.out.println("小煞：" + BRANCHES[LITTLE_GOBLIN[tbd.getMonthB()]]);
		if(sb.length()>0){
			System.err.println("触犯忌时");
			System.err.println(sb.toString());
		}
	}

}
