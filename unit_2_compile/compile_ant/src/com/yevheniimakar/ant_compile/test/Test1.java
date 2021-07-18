package com.yevheniimakar.ant_compile.test;

import org.apache.commons.text.CaseUtils;
import com.yevheniimakar.ant_compile.test.test2.Test2;

public class Test1 {

    public void test() {
        System.out.println("compile ANT class Test1");
        System.out.println(CaseUtils.toCamelCase("compile.ANT.class.Test1", false, new char[]{'.'}));
        new Test2().test();
    }
}