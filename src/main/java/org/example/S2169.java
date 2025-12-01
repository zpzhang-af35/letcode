package org.example;

public class S2169 {
    public int countOperations(int num1, int num2) {
        int ret = 0;
        int max = num1;
        int min = num2;
        while (max != 0 && min != 0) {
            if (max < min){
                int temp = max;
                max = min;
                min = temp;
            }
            ret += max / min;
            max = max % min;
        }
        return ret;
    }
}
