package com.group1;

import java.util.ArrayList;

public class Session implements java.io.Serializable{
    private String sessionName;
    private int totalVacancy;
    private ArrayList<Student> resgisteredstudent = new ArrayList<>();

    public Session(String sessionName){
        this.sessionName = sessionName;
    }

    public Session(String sessionName, int totalVacancy){
        this.sessionName = sessionName;
        this.totalVacancy = totalVacancy;
    }

    public String GetName(){
        return sessionName;
    }


    public int GetTotalVacancy(){ return totalVacancy; }

    public ArrayList<Student> getResgisteredstudent()
    {
        return resgisteredstudent;
    }
}
