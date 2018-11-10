package com.group1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;

public class Student implements java.io.Serializable
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
	public String GetStudentName(){
        return studentName;
        }
	public HashMap<String,ArrayList<AssessmentComponent>> GetCourseAndResult(){
			return courseAndResult;
	}
	public void ClearHashmapValue (String key)
	{
		courseAndResult.get(key).clear();
	}

}
	

