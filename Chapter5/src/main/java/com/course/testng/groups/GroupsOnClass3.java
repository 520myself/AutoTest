package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupsOnClass3 {
    public void tercher1(){
        System.out.println("Class3中的teacher1");
    }
    public void tercher2(){
        System.out.println("Class3中的teacher2");
    }
    public void tercher3(){
        System.out.println("Class3中的teacher3");
    }
}
