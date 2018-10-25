package com.group1;

public class Session {
    String sessionName;
    int totalVacancy;

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
}
