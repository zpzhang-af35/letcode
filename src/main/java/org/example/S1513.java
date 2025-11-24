package org.example;

public class S1513 {
    public int numSub(String s) {
        long total = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '1'){
                int subStr = 1;
                while (i+1 < s.length() && s.charAt(i+1) == '1'){
                    i++;
                    subStr += 1;
                }
                total += possibleSubN(subStr);
            }
        }
        total %= 1000000007;
        return (int)total;
    }

    private long possibleSubN(long n){
        return (1+n)*n/2;
    }
}
