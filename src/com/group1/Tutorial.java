package com.group1;

public class Tutorial extends Session implements java.io.Serializable{
    public Tutorial(String sessionName) {super(sessionName);}
    public Tutorial(String sessionName, int totalVacancy){
        super(sessionName, totalVacancy);
    }


}
