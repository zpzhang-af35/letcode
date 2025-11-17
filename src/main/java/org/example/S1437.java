package org.example;

public class S1437 {
    public boolean kLengthApart(int[] nums, int k) {
        int lastPos = -1;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 1){
                if(lastPos == -1){
                    lastPos = i;
                } else {
                    if(i - lastPos > k){
                        lastPos = i;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
