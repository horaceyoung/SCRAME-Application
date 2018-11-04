package com.group1;

import Exceptions.NameNotValidException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;

public class StudentManager {



    
    public static void printCourseStatistics(String courseCodeStatistics) throws FileNotFoundException{
        
    	//get the weight of exam and coursework
    	File componentFile = new File("data/Component.txt");
        Scanner ComponentScanner = new Scanner (componentFile);
        float examWeightage=0;
        float courseworkWeightage=0;
        while(ComponentScanner.hasNext()) {
    		String[] courseName = ComponentScanner.nextLine().split("\t");
    		if (courseName[0].toUpperCase().equals(courseCodeStatistics.toUpperCase())) {
    			String[] tempExam = courseName[1].split(": ");
    			String[] tempcoursework = courseName[2].split(": ");
    			examWeightage = Float.parseFloat(tempExam[1]);
    			courseworkWeightage = Float.parseFloat(tempcoursework[1]);
    			}
    		}
        
    	File resultsCourseFile = new File("data/Results.txt");
        Scanner resultsScanner = new Scanner(resultsCourseFile);
        float examResult = 0;
        float courseWorkResult = 0;
        int studentCount=0;
        
        //read Results file to get results
        while (resultsScanner.hasNext()) {
            String[] courseCode = resultsScanner.nextLine().split("\t");
            if(courseCode.length>1) {
            	if (courseCode[1].toUpperCase().equals(courseCodeStatistics.toUpperCase())){
                	examResult += Float.parseFloat(courseCode[2]);
                	courseWorkResult += Float.parseFloat(courseCode[3]);
                	studentCount++;
                	}
            	}
        }
        System.out.println(
    			"\n\tExam Result: " + examResult/studentCount + "%" + 
    			"\n\tcoursework Result: " + courseWorkResult/studentCount + "%" +
    			"\n\tOverall(exam + coursework): " + (examResult * examWeightage + courseWorkResult * courseworkWeightage)/studentCount + "%");
    }    
}
