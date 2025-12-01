package org.example;

public class S3234 {
    public int numberOfSubstrings(String s) {
        int[][] zeroCount = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (i == j) {
                    zeroCount[i][j] = s.charAt(i) == '0' ? 1 : 0;
                } else {
                    zeroCount[i][j] = zeroCount[i][j - 1] + (s.charAt(j) == '0' ? 1 : 0);
                }
            }
        }

        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                int oneCount = j - i + 1 - zeroCount[i][j];
                if(oneCount >= zeroCount[i][j] * zeroCount[i][j]){
                    count++;
                }
            }
        }

        return count;
    }
}
