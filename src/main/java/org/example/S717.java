package org.example;

public class S717 {
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        for (; i < bits.length - 1; i++) {
            if (bits[i] == 0){
                continue;
            } else {
                i++;
            }
        }
        return i == (bits.length - 1);
    }
}
