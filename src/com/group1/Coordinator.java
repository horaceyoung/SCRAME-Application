package com.group1;

public class Coordinator {
    private String name;
    private String school;
    public Coordinator(String name, String school){
        this.name = name;
        this.school = school;
    }

    public String getCoordinatorName(){
        return name;
    }

    public String getCoordinatorSchool(){
        return school;
    }
}
