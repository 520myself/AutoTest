package com.course.model;

import lombok.Data;

@Data
public class User{
    private int id;
    private String username;
    private String password;
    private String name;
    private String create_time;
    private String state;
}
