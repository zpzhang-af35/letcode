package org.example;

import java.util.HashSet;
import java.util.Set;

public class S1015 {
    public int smallestRepunitDivByK(int k) {
        long start = 1;
        int n = 1;
        Set<Long> existMod = new HashSet<>();
        while (true){
            long curMod = start % k;
            System.out.println(curMod);
            if(curMod == 0){
                return n;
            } else {
                if(existMod.contains(curMod)){
                    return -1;
                } else {
                    existMod.add(curMod);
                    start = (start%k) * 10 + 1;
                    n++;
                }
            }
        }
    }
}
