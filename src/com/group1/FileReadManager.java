package com.group1;

import Exceptions.CourseNameNotValidException;
import Exceptions.TutorialLabNotAvailableException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReadManager {
    public static boolean CheckCourseExists(String courseTitle) throws IOException{
        File courseFile = new File("data/Courses.txt");
        Scanner coursesScanner = new Scanner(courseFile);
        while(coursesScanner.hasNext()){
            String[] currentCourse = coursesScanner.nextLine().split("\t");
            if (currentCourse[0].equals(courseTitle)){
                return true;
            }
        }
        return false;
    }

    public static boolean CheckStudentExists(String matric) throws IOException {
        File studentFile = new File("data/Students.txt");
        Scanner studentSC= new Scanner(studentFile);
        while(studentSC.hasNext()){
            String[] currStudent= studentSC.nextLine().split("\t");
            if (currStudent[1].equals(matric))
                return true;
        }
        return false;
    }

    public static String GetStudentInfo(String matric) throws IOException{
        File studentFile = new File("data/Students.txt");
        Scanner studentSC= new Scanner(studentFile);
        while(studentSC.hasNext()){
            String[] currStudent= studentSC.nextLine().split("\t");
            if (currStudent[1].equals(matric))
                return currStudent[0];
        }
        return "default";
    }

    public static boolean GetCourseSessions(String courseTitle, Course course) throws IOException, TutorialLabNotAvailableException{
        File courseFile = new File("data/Tutorials.txt");
        Scanner coursesScanner = new Scanner(courseFile);
        boolean available = false;
        while(coursesScanner.hasNext()){
            String[] currentCourse = coursesScanner.nextLine().split("\t");
            if (currentCourse[0].equals(courseTitle)&&Integer.valueOf(currentCourse[3])>0){
                available = true;
                course.AddTutorial(new Tutorial(currentCourse[1]));
                System.out.println("Tutorials: " + currentCourse[1]+ " total slots: " + currentCourse[2] + " / available slots: " + currentCourse[3] + "\n");

            }
        }

        courseFile = new File("data/Labs.txt");
        coursesScanner = new Scanner(courseFile);
        while(coursesScanner.hasNext()){
            String[] currentCourse = coursesScanner.nextLine().split("\t");
            if (currentCourse[0].equals(courseTitle)&& Integer.valueOf(currentCourse[3])>0){
                available = true;
                course.AddLab(new Lab(currentCourse[1]));
                System.out.println("Labs: " + currentCourse[1]+ " total slots: " + currentCourse[2] + " / available slots: " + currentCourse[3] + "\n");
            }
        }

        return available;

    }

    public static void PrintTutorialVacancy(String courseTitle) throws IOException, TutorialLabNotAvailableException{
        File courseFile = new File("data/Tutorials.txt");
        Scanner coursesScanner = new Scanner(courseFile);
        boolean available = false;
        int occupied;
        System.out.println("The Vacancy of Tutorial Groups of " + courseTitle + " is as following: ");
        while(coursesScanner.hasNext()){
            String[] currentCourse = coursesScanner.nextLine().split("\t");
            if (currentCourse[0].equals(courseTitle)){
                available=true;
                occupied = Integer.parseInt(currentCourse[2])-Integer.parseInt(currentCourse[3]);
                System.out.print("Tutorial Group "+currentCourse[1]+"\t");
                System.out.println(occupied+ "/" + currentCourse[2] + "\n");

            }
        }
        if(!available)
            throw new TutorialLabNotAvailableException();
    }
    public static void PrintLabVacancy(String courseTitle) throws IOException, TutorialLabNotAvailableException{
        File courseFile = new File("data/Labs.txt");
        Scanner coursesScanner = new Scanner(courseFile);
        boolean available = false;
        int occupied;
        System.out.println("The Vacancy of Lab Groups of " + courseTitle + " is as following: ");
        while(coursesScanner.hasNext()){
            String[] currentCourse = coursesScanner.nextLine().split("\t");
            if (currentCourse[0].equals(courseTitle)){
                available=true;
                occupied = Integer.parseInt(currentCourse[2])-Integer.parseInt(currentCourse[3]);
                System.out.print("Lab Group "+currentCourse[1]+"\t");
                System.out.println(occupied+ "/" + currentCourse[2] + "\n");

            }
        }
        if(!available)
            throw new TutorialLabNotAvailableException();
    }

}