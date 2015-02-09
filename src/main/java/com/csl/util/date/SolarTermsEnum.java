package com.csl.util.date;
public enum SolarTermsEnum { 
LICHUN(2,"立春 ",0),//--立春 
YUSHUI(2,"雨水",1),//--雨水 
JINGZHE(3,"惊蛰 ",2),//--惊蛰 
CHUNFEN(3,"春分 ",3),//春分 
QINGMING(4,"清明",4),//清明 
GUYU(4,"谷雨 ",5),//谷雨 
LIXIA(5,"立夏 ",6),//立夏 
XIAOMAN(5,"小满 ",7),//小满 
MANGZHONG(6,"芒种 ",8),//芒种 
XIAZHI(6,"夏至 ",9),//夏至 
XIAOSHU(7,"小暑 ",10),//小暑 
DASHU(7,"大暑 ",11),//大暑 
LIQIU(8,"立秋 ",12),//立秋 
CHUSHU(8,"处暑 ",13),//处暑 
BAILU(9,"白露 ",14),//白露 
QIUFEN(9,"秋分 ",15),//秋分 
HANLU(10,"寒露 ",16),//寒露 
SHUANGJIANG(10,"霜降 ",17),//霜降 
LIDONG(11,"立冬 ",18),//立冬 
XIAOXUE(11,"小雪 ",19),//小雪 
DAXUE(12,"大雪 ",20),//大雪 
DONGZHI(12,"冬至 ",21),//冬至 
XIAOHAN(1,"小寒 ",22),//小寒 
DAHAN(1,"大寒 ",23);//大寒 
private int month;
private String name;
private int order;
SolarTermsEnum(int month ,String name,int order){
	this.month = month;
	this.name = name;
}
public String getName() {
	return name;
}
public int getMonth() {
	return month;
}
public int getOrder() {
	return order;
}


} 