package com.group1;

import java.util.ArrayList;
import java.util.HashMap;

public class Student implements java.io.Serializable, People
{
	private String studentName;
	private String marticNumber;
	private HashMap<String, ArrayList<AssessmentComponent>> courseAndResult = new HashMap<>();
	
	public Student(String studentName,String marticNumber){
	    this.studentName = studentName;
	    this.marticNumber = marticNumber;
	}
    public String getMatricNumber(){
        return this.marticNumber;
    }
	public String getName(){
        return studentName;
        }
	public HashMap<String,ArrayList<AssessmentComponent>> getCourseAndResult(){
			return courseAndResult;
	}

	@Override
	public void setName(String name) {
		studentName=name;
	}
}
	

