package com.group1;

import java.util.ArrayList;

public class Session implements java.io.Serializable{
    private String sessionName;
    private int totalVacancy;
    private ArrayList<Student> registeredStudent = new ArrayList<>();


    public Session(String sessionName){
        this.sessionName = sessionName;
    }

    public Session(String sessionName, int totalVacancy){
        this.sessionName = sessionName;
        this.totalVacancy = totalVacancy;
    }
    public ArrayList<Student> getRegisteredStudent(){return registeredStudent;}

    public String getSessionName(){
        return sessionName;
    }

    public ArrayList<Student> getStudentList(){ return registeredStudent; }

    public int getTotalVacancy(){ return totalVacancy; }

    public boolean haveVacancy(){
        if(registeredStudent.size()<totalVacancy)
            return true;
        return false;
    }
}
