package org.example;

import java.util.ArrayList;
import java.util.List;

public class S1018 {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        int x = 0;
        List<Boolean> ret = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            x = (x * 2 + nums[i]) % 5;
            ret.add(x % 5 == 0);
        }
        return ret;
    }
}
