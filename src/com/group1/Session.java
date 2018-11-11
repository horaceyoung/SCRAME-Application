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

<<<<<<< HEAD
    public ArrayList<Student> getStudentList(){ return registeredStudent; }

    public int getTotalVacancy(){ return totalVacancy; }
=======
    public int GetTotalVacancy(){ return totalVacancy; }
>>>>>>> a4991a1b4d2351ecd2f8bb0fe63db0f105609121

    public boolean haveVacancy(){
        if(registeredStudent.size()<totalVacancy)
            return true;
        return false;
    }
}
