package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnAnnotion {
    @Test(invocationCount = 10,threadPoolSize = 3)
    public void test1(){
        System.out.println(111);
        System.out.println("Thread id:"+Thread.currentThread().getId());
    }
}
