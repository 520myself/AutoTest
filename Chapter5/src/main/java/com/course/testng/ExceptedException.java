package com.course.testng;

import org.testng.annotations.Test;

public class ExceptedException {
    /*
    * 什么时候用到异常测试
    * 期望结果某一个异常时
    * 比如：传入不合法参数，程序抛出的异常
    * 预期结果就是这个异常
    */
    //这是一个测试结果会失败的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExcrptionfailed(){
        System.out.println("这是一个失败的异常测试");
    }
    //这是一个成功的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExcrptionSuccess(){
        System.out.println("这是我的异常测试");
        throw new RuntimeException();
    }
}
