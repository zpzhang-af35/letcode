package org.example;

import java.util.*;

public class S1930 {
    //超时
    public int countPalindromicSubsequenceV1(String s) {
        Set<Character> oneCharsubStr = new HashSet<>();
        Set<String> twoCharsubStr = new HashSet<>();
        Set<String> threeCharsubStr = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            Character curChar = s.charAt(i);
            // try make 3 char str
            twoCharsubStr.stream().forEach(
                    x -> {
                        if (x.charAt(0) == curChar){
                            threeCharsubStr.add(x + curChar);
                        }
                    }
            );

            oneCharsubStr.stream().forEach(
                    x -> twoCharsubStr.add(new String(new char[]{x,curChar}))
            );

            oneCharsubStr.add(curChar);
        }

        return threeCharsubStr.size();
    }

    public int countPalindromicSubsequence(String s) {
        Map<Character,List<Integer>> charPos = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character curChar = s.charAt(i);
            if(charPos.containsKey(curChar)){
                if(charPos.get(curChar).size() == 2) {
                    charPos.get(curChar).set(1,i);
                } else {
                    charPos.get(curChar).add(i);
                }
            } else {
                int finalI = i;
                charPos.put(curChar,new ArrayList() {{add(finalI);}});
            }
        }

        Set<String> subString = new HashSet<>();

        charPos.forEach((k,v) -> {
            if(v.size() == 2) {
                for (int i = v.get(0) + 1; i < v.get(1); i++) {
                    subString.add(
                            new String(new char[] {k,s.charAt(i),k})
                    );
                }
            }
        });

        return subString.size();
    }
}
