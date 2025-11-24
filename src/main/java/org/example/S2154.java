package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class S2154 {
    public int findFinalValue(int[] nums, int original) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        Collections.sort(list);
        int result = original;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) > result) {
                return result;
            } else if(list.get(i) == result){
                result *= 2;
            }
        }
        return result;
    }
}
