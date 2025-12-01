package org.example;

public class S3512 {
    public int minOperations(int[] nums, int k) {
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            ret += nums[i] % k;
            ret %= k;
        }
        return ret;
    }
}
