package com.group1;

import java.util.ArrayList;
import java.util.HashMap;

public class Student
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
	
	public static String getNameFromMatric (String marticNumber) throws IOException {

			File courseFile = new File("data/Students.txt");
			Scanner studentScanner = new Scanner(courseFile);
			String name = "";

			while(studentScanner.hasNext()) {
				String[] studentInfo = studentScanner.nextLine().split("\t");
				if(marticNumber.equals(studentInfo[1])) {
					name = studentInfo[0];
					return name;
				}
			}
			return name;
		}


        }
	

