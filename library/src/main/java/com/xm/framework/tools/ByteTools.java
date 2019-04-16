package com.xm.framework.tools;

/**
 * Created by Mouse on 2019/4/16.
 */
public class ByteTools {

    public static byte[] merge(byte[]... bytes) {

        int len = 0;
        for (int i = 0; i < bytes.length; i++) {
            len += bytes[i].length;
        }
        byte[] array = new byte[len];
        int count = 0;
        for (int i = 0; i < bytes.length; i++) {
            System.arraycopy(bytes[i], 0, array, count, bytes[i].length);
            count += bytes[i].length;
        }
        return array;
    }
}
