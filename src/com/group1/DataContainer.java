package com.group1;

import java.util.ArrayList;

public class DataContainer implements java.io.Serializable{
    public DataContainer(){

    }
    public ArrayList<Student> studentsList = new ArrayList<>();
    public ArrayList<Course> courseList = new ArrayList<>();
    public boolean studentExists(String matricNumber){
        for(Student student:studentsList){
            if(matricNumber.equals(student.getMatricNumber()))
                return true;
        }
        return false;
    }

}
