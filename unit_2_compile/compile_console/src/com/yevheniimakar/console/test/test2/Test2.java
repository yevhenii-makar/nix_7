package com.yevheniimakar.console.test.test2;

import org.apache.commons.math3.*;
import org.apache.commons.math3.analysis.function.Tan;

public class Test2{
    public void test(){
        System.out.println("console compile class Test2");
        Tan tan = new Tan();
        System.out.println("lib org.apache.commons.math3"+tan.value(45.0));
    }

}