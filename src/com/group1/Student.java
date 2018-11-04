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
	
	public static String getNameFromMatric (String marticNumber) {

			File courseFile = new File("data/Students.txt");
			String name = "default";
			try{

				Scanner studentScanner = new Scanner(courseFile);
				while(studentScanner.hasNext()) {
					String[] studentInfo = studentScanner.nextLine().split("\t");
					if(marticNumber.equals(studentInfo[1])) {
						name = studentInfo[0];
						return name;
					}
				}
			}
			catch (Exception e){
				System.out.println(e.getMessage());
			}

			return name;
		}
	}
	

