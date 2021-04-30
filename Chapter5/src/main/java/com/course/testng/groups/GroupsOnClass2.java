package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass2 {
    public void stu1(){
        System.out.println("Class2中的stu1");
    }
    public void stu2(){
        System.out.println("Class2中的stu2");
    }
    public void stu3(){
        System.out.println("Class2中的stu3");
    }
}
