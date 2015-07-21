package com.csl.util.date;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaiPan {
    private static String[][] data={{
        "┏━━━━━━━━━━━━━┳━━━━━━━━━┳━━━━━━━━━━━━━━┓",
        "┃ ╲  天德    x1 ┃  白虎  x2┃  玉堂   x3  ╱ ┃",
        "┃          ┏━━━━━━━╋━━━━━━━━━╋━━━━━━┓              ┃",
        "┃金匮   ┃    丁      ┃    壬          ┃    乙    ┃天牢       ┃",
        "┃  x12┃   惊门   ┃   开门        ┃   休门  ┃ x4   ┃",
        "┣━━━━━╋━━━━━━━╋━━━━━━━━━╋━━━━━━╋━━━━━━━┫",
        "┃朱雀   ┃    丙      ┃    癸          ┃    庚    ┃玄武       ┃",
        "┃  x11┃   死门   ┃    戊          ┃   生门   ┃ x5  ┃",
        "┣━━━━━╋━━━━━━━╋━━━━━━━━━╋━━━━━━╋━━━━━━━┫",
        "┃天刑  ┃    辛       ┃    甲           ┃    己    ┃司命      ┃",
        "┃          ┃   景门    ┃   杜门        ┃   伤门   ┃ x6  ┃",
        "┃ x10┗━━━━━━━╋━━━━━━━━━━╋━━━━━━━┛          ┃",
        "┃ ╱   明堂   x9 ┃  青龙  x8┃   勾陈   x7 ╲   ┃",
        "┗━━━━━━━━━━━━━┳━━━━━━━━━┳━━━━━━━━━━━━━━┛",
}};
    private TrunkBranchDate date ;
   public PaiPan(TrunkBranchDate date) {
       this.date = date;
   }
   private  int[] sort= {3,2,0,11,0,11,9,8,6,5};
   private String[] twelve={"福","曹","府","风","雷","雨","云","符","印","关","轴","贼"};
   private Pattern x = Pattern.compile("[x](\\d+)");
   public void print(){
       int time1 =0;
       int time2 = 2;
       
       String[] currentData = data[time1];
       for (String input : currentData) {
        Matcher matcher = x.matcher(input);
         while(matcher.find()){
             String s = matcher.group(1);
             int index = Integer.parseInt(s);
             index = getIndex(sort[time2]+index-1);
             input = input.replace(matcher.group(), twelve[index]);
         }
         System.out.println(input);
    }
   }
   public static void main(String[] args) {
    new PaiPan(new TrunkBranchDate()).print();
}
   
   private int getIndex(int index){
       return index%12;
   }

}
