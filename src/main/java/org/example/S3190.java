package org.example;

public class S3190 {
    public int minimumOperations(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i] % 3 == 0 ? 0 : 1;
        }
        return count;
    }
}
