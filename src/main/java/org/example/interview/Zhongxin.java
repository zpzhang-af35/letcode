package org.example.interview;

import java.util.*;

public class Zhongxin {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String line = in.nextLine();
        line = line.substring(1,line.length() - 1);
        String[] section = line.split("\\)\\(");
        Map<Long,Long> numsCount = new HashMap();
        for(String sec : section){
            sec = sec.substring(1);
            Long num = Long.valueOf(sec);
            if(numsCount.containsKey(num)){
                numsCount.put(num,numsCount.get(num)+1);
            }else{
                numsCount.put(num,1L);
            }
        }
        long allConst = 1;
        for(Map.Entry entry : numsCount.entrySet()){
            for (int i = 0; i < (Long)entry.getValue(); i++) {
                allConst *= (Long)entry.getKey();
            }
        }

        long ret = 0;
        for(Map.Entry entry : numsCount.entrySet()){
            ret += (allConst/(Long)entry.getKey()) * (Long)entry.getValue();
            ret %= 10007;
        }
        System.out.println(ret);
    }
}
