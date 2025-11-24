package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class S1262 {

    int[][] maxsum;
    public int maxSumDivThree(int[] nums) {
        int sum = 0;
        List<Integer> mod1 = new ArrayList<>();
        List<Integer> mod2 = new ArrayList<>();

        for (int num : nums) {
            if (num % 3 == 0) {
                sum += num;
            } else if (num % 3 == 1) {
                mod1.add(num);
            } else {
                mod2.add(num);
            }
        }

        Comparator<Integer> desc = (o1, o2) -> o2 - o1;

        mod1.sort(desc);
        mod2.sort(desc);

        maxsum = new int[mod1.size()+1][mod2.size()+1];
        for (int i = 0; i < mod1.size()+1; i++) {
            for (int j = 0; j < mod2.size()+1; j++) {
                maxsum[i][j] = -1;
            }
        }

        return sum + serchv3(mod1,mod2);
    }

    /**
     * long time
     * @param mod1
     * @param mod2
     * @return
     */
    private int serchv1(List<Integer> mod1, List<Integer> mod2){
        if(mod1.isEmpty() && mod2.size() < 3 || mod2.isEmpty() && mod1.size() < 3){
            return 0;
        }
        int possible1 = 0;
        if(mod1.size() >= 3){
            possible1 = mod1.get(0) + mod1.get(1) + mod1.get(2) + serchv1(mod1.subList(3,mod1.size()),mod2);
        }

        int possible2 = 0;
        if(mod2.size() >= 3){
            possible2 = mod2.get(0) + mod2.get(1) + mod2.get(2) + serchv1(mod1,mod2.subList(3,mod2.size()));
        }

        int possible3 = 0;
        if (!mod2.isEmpty() && !mod1.isEmpty()){
            possible3 = mod1.get(0) + mod2.get(0) + serchv1(mod1.subList(1,mod1.size()), mod2.subList(1, mod2.size()));
        }

        if (possible1 > possible2){
            return Math.max(possible1,possible3);
        } else {
            return Math.max(possible2,possible3);
        }
    }

    /**
     * wrong
     * @param mod1
     * @param mod2
     * @return
     */
    private int serchv2(List<Integer> mod1, List<Integer> mod2) {
        if(mod1.isEmpty() && mod2.size() < 3 || mod2.isEmpty() && mod1.size() < 3){
            return 0;
        }

        int possible1 = 0;
        if(mod1.size() >= 3){
            possible1 = mod1.get(0) + mod1.get(1) + mod1.get(2);
        }

        int possible2 = 0;
        if(mod2.size() >= 3){
            possible2 = mod2.get(0) + mod2.get(1) + mod2.get(2);
        }

        int possible3 = 0;
        if (!mod2.isEmpty() && !mod1.isEmpty()){
            possible3 = mod1.get(0) + mod2.get(0);
        }

        if (possible1 > possible2 && possible1 > possible3) {
            return mod1.get(0) + mod1.get(1) + mod1.get(2) + serchv2(mod1.subList(3,mod1.size()),mod2);
        } else if (possible2 > possible1 && possible2 > possible3){
            return mod2.get(0) + mod2.get(1) + mod2.get(2) + serchv2(mod1,mod2.subList(3,mod2.size()));
        } else if (possible3 > possible1 && possible3 > possible2){
            return mod1.get(0) + mod2.get(0) + serchv2(mod1.subList(1,mod1.size()), mod2.subList(1, mod2.size()));
        } else if (possible1 == possible2 && possible1 > possible3){
            return Math.max(
                    mod1.get(0) + mod1.get(1) + mod1.get(2) + serchv2(mod1.subList(3,mod1.size()),mod2),
                    mod2.get(0) + mod2.get(1) + mod2.get(2) + serchv2(mod1,mod2.subList(3,mod2.size()))
            );
        } else if (possible1 == possible3 && possible1 > possible2) {
            return Math.max(
                    mod1.get(0) + mod1.get(1) + mod1.get(2) + serchv2(mod1.subList(3,mod1.size()),mod2),
                    mod1.get(0) + mod2.get(0) + serchv2(mod1.subList(1,mod1.size()), mod2.subList(1, mod2.size()))
            );
        } else if (possible2 == possible3 && possible2 > possible1) {
            return Math.max(
                    mod2.get(0) + mod2.get(1) + mod2.get(2) + serchv2(mod1,mod2.subList(3,mod2.size())),
                    mod1.get(0) + mod2.get(0) + serchv2(mod1.subList(1,mod1.size()), mod2.subList(1, mod2.size()))
            );
        } else {
            int m1 = mod1.get(0) + mod1.get(1) + mod1.get(2) + serchv2(mod1.subList(3,mod1.size()),mod2);
            int m2 = mod2.get(0) + mod2.get(1) + mod2.get(2) + serchv2(mod1,mod2.subList(3,mod2.size()));
            int m3 = mod1.get(0) + mod2.get(0) + serchv2(mod1.subList(1,mod1.size()), mod2.subList(1, mod2.size()));
            if(m1 > m2) {
                return Math.max(m1,m3);
            }else {
                return Math.max(m2,m3);
            }
        }
    }

    /**
     * mem out
     * @param mod1
     * @param mod2
     * @return
     */
    private int serchv3(List<Integer> mod1, List<Integer> mod2){
        if(maxsum[mod1.size()][mod2.size()] != -1){
            return maxsum[mod1.size()][mod2.size()];
        }

        if(mod1.isEmpty() && mod2.size() < 3 || mod2.isEmpty() && mod1.size() < 3){
            maxsum[mod1.size()][mod2.size()] = 0;
            return 0;
        }
        int possible1 = 0;
        if(mod1.size() >= 3){
            possible1 = mod1.get(0) + mod1.get(1) + mod1.get(2) + serchv3(mod1.subList(3,mod1.size()),mod2);
        }

        int possible2 = 0;
        if(mod2.size() >= 3){
            possible2 = mod2.get(0) + mod2.get(1) + mod2.get(2) + serchv3(mod1,mod2.subList(3,mod2.size()));
        }

        int possible3 = 0;
        if (!mod2.isEmpty() && !mod1.isEmpty()){
            possible3 = mod1.get(0) + mod2.get(0) + serchv3(mod1.subList(1,mod1.size()), mod2.subList(1, mod2.size()));
        }

        int max = 0;
        if (possible1 > possible2){
            max = Math.max(possible1,possible3);
        } else {
            max = Math.max(possible2,possible3);
        }
        maxsum[mod1.size()][mod2.size()] = max;
        return max;
    }
}