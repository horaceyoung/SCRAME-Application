package com.group1;


public class Professor implements java.io.Serializable {
    public String name;
    public String school;
    public Professor(String name, String school){
        this.name = name;
        this.school = school;
    }
}
