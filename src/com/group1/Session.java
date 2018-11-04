package com.group1;
import Exceptions.NameNotValidException;
import Exceptions.TutorialLabNameInvalidException;
import Exceptions.TutorialLabNumberInvalidException;
import Exceptions.WeightageNotValidException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Session implements java.io.Serializable{
    String sessionName;
    int totalVacancy;
    private ArrayList<Student> registeredStudent = new ArrayList<>();


    public Session(String sessionName){
        this.sessionName = sessionName;
    }

    public Session(String sessionName, int totalVacancy){
        this.sessionName = sessionName;
        this.totalVacancy = totalVacancy;
    }
    public ArrayList<Student> GetRegisteredStudent(){return registeredStudent;}

    public String GetName(){
        return sessionName;
    }

    public ArrayList<Student> getStudentList(){
        return registeredStudent;
    }

    public int GetTotalVacancy(){ return totalVacancy; }
}
