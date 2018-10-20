package com.group1;

public class Session {
    String sessionName;
    int totalVacancy;

    public Session(String sessionName, int totalVacancy){
        this.sessionName = sessionName;
        this.totalVacancy = totalVacancy;
    }

    public String getName(){
        return sessionName;
    }

    public int getTotalVacancy(){
        return totalVacancy;
    }
}
