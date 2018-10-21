package com.group1;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	private String studentName;
	private String marticNumber;
	private HashMap<String, ArrayList<Session>> courseRegistered;
	private HashMap<String, ArrayList<AssessmentComponent>> courseResult;
	
	public Student(String studentName,String marticNumber){
           this.studentName = studentName;
	   this.marticNumber = marticNumber;
        }
	public String GetStudentName(){
        return studentName;
        }
	public String GetMarticNumber(){
        return marticNumber;
        }

        }
	
}
