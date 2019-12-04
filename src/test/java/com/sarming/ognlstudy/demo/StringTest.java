package com.sarming.ognlstudy.demo;

import org.junit.jupiter.api.Test;

public class StringTest {

    /**
     * string + 号原理，使用javap 命令 反汇编，查看Java编译器为我们生成的字节码可以看出底层使用的是StringBuilder处理的。
     */
    @Test
    public void testString() {
        System.out.println(new String("hello world") + new String("lp"));
    }
}
