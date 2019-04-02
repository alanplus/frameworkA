package com.xm.framework.tools;

public class RandomTools {

    public static int getRandomInt(int max, int min) {
        return min + (int) (Math.random() * (max - min + 1) + 1) - 1;
    }
}
