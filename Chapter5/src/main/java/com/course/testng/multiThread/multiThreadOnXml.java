package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class multiThreadOnXml {
    @Test
    public void test1(){
        System.out.println(111);
        System.out.println("Thread id:"+Thread.currentThread().getId());
    }
    @Test
    public void test2(){
        System.out.println(222);
        System.out.println("Thread id:"+Thread.currentThread().getId());
    }
    @Test
    public void test3(){
        System.out.println(333);
        System.out.println("Thread id:"+Thread.currentThread().getId());
    }
}
