package com.group1;

public class Lab extends Session implements java.io.Serializable{
    public Lab(String sessionName, int totalVacancy){
        super(sessionName, totalVacancy);
    }
    public Lab(String sessionName) {super(sessionName);}
}
