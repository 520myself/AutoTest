package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnotation {
    //Test最基本的注解，把方法标记为测试的一部分
    @Test
    public void testCase1(){
        System.out.println("这是测试用例1");
    }
    @Test
    public void testCase2(){
        System.out.println("这是测试用例2");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("这是在测试方法之前运行的");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("这是在测试方法之后运行的");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("在类运行之前运行");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("在类运行之后运行");
    }
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("BeforeSuite测试套件");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("AfterSuite测试套件");
    }
}
