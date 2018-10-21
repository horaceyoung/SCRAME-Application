package com.group1;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	private String studentName;
	private String studentMarticNumber;
	private HashMap<String, ArrayList<AssessmentComponent>> courseRegistered;
	
	public Student(String studentName,String studentMarticNumber){
        this.studentName = studentName;
	this.studentMarticNumber = studentMarticNumber;
    }
}
