package com.yevheniimakar.compile_maven.test;

import org.apache.commons.text.CaseUtils;
import com.yevheniimakar.compile_maven.test.test2.Test2;

public class Test1 {

    public void test() {
        System.out.println("maven compile class Test1");
        System.out.println(CaseUtils.toCamelCase("maven.compile.class.Test1", false, new char[]{'.'}));
        new Test2().test();
    }
}