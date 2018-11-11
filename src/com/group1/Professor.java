package com.group1;


public class Professor implements java.io.Serializable,People{
    public String name;
    public String school;

    public Professor(String name, String school){
        this.name = name;
        this.school = school;
    }
    public String getName(){return this.name;}
    public void setName(String name){
        this.name=name;
    }
}
