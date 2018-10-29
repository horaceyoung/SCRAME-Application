package com.group1;

import Exceptions.NameNotValidException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;

public class StudentManager {
    public static Student AddStudent (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Add Student: Please input the Student Name.");        
        String studentName = "Default";
        String matric = "Default";
        boolean nameValid = false;
        while(!nameValid) {
            studentName = sc.nextLine();
            try {
                if (!InputManager. ValidateNameInput(studentName)) 
                      throw new NameNotValidException ();
                System.out.println("Please input the Student's Matric Number.");
                matric = sc.nextLine();
                if(FileReadManager.CheckStudentExists(matric)){
                            System.out.println("Add Student Failed: Student has been added.");
                        }
                else{
                nameValid = true;
                System.out.println("Add student Success: student " + studentName + ": "+  matric+ " has been successfully added");
                }
            } 
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        return new Student(studentName,matric);
    }
    
    public static void printTranscript(String studentMatricTranscript) throws FileNotFoundException{
        File resultsFile = new File("data/Results.txt");
        Scanner resultsScanner = new Scanner(resultsFile);
        String studentName;
       
        File componentFile = new File("data/Component.txt");
        Scanner ComponentScanner = new Scanner (componentFile);
        
        //read Results file to get results
        while (resultsScanner.hasNext()) {
            String[] matricNumber = resultsScanner.nextLine().split("\t");
            if (matricNumber[0].toUpperCase().equals(studentMatricTranscript.toUpperCase())) {
            	studentName = Student.getNameFromMatric(studentMatricTranscript);
            	
            	//get overall score for the course
        		String number2 = matricNumber[2];
				float result2 = Float.parseFloat(number2.trim());
    			String number3 = matricNumber[3];
  				float result3 = Float.parseFloat(number3.trim());	
  				float result = result2 + result3;
				
            	System.out.println(studentName);
            	for (int i = 4; i< matricNumber.length; i++) {
            		
            		System.out.println(
            				"Course Name: " + matricNumber[1] + 
            				"\n\tExam Result: " + matricNumber[2] + 
            				"\n\tcoursework Result: " + matricNumber[3] + 
            				"\n\tOverall(exam + coursework): " + result +
            				"\n\tCoursework Component: " + matricNumber[i]
            				);
            	}
            	 //read Component file to get individual components weight
            	while(ComponentScanner.hasNext()) {
            		String[] courseName = ComponentScanner.nextLine().split("\t");
            		if (courseName[0].toUpperCase().equals(matricNumber[1].toUpperCase())) {
            			System.out.println("Component Weightage:");
            			for (int j = 1; j< courseName.length; j++) {
                			System.out.print("\t" + courseName[j] + "\n");
            			}
            		}
            	}
            }
        }
    }
    
    public static void printCourseStatistics(String courseCodeStatistics) throws FileNotFoundException{
        File resultsCourseFile = new File("data/Results.txt");
        Scanner resultsScanner = new Scanner(resultsCourseFile);
        float examResult = 0;
        float courseWorkResult = 0;
        //read Results file to get results
        while (resultsScanner.hasNext()) {
            String[] courseCode = resultsScanner.nextLine().split("\t");
/*            if (courseCode[1].toUpperCase().equals(courseCodeStatistics.toUpperCase())){
            	examResult += Float.parseFloat(courseCode[2]);
            	courseWorkResult += Float.parseFloat(courseCode[3]);
            }
            else {
            	continue;
            }*/
            System.out.println(courseCode.length);
        }
            System.out.println(
    				"\n\tExam Result: " + examResult + "%" + 
    				"\n\tcoursework Result: " + courseWorkResult + "%" +
    				"\n\tOverall(exam + coursework): " + (examResult + courseWorkResult)/2 + "%");
    }    
}
