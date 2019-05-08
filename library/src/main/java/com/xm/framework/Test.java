package com.xm.framework;

import com.xm.framework.tools.StringTools;

/**
 * Created by Mouse on 2019/4/1.
 */
public class Test {

    public static void main(String[] args) {
        String name = "asdada_123123123AAAA";
        boolean alvalidUserName = StringTools.isAlvalidUserName(name);
        System.out.println(alvalidUserName);
    }
}
