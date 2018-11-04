package com.group1;

import Exceptions.CourseNotFoundException;
import Exceptions.TutorialLabNotAvailableException;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadingManager
{

    public ArrayList<Tutorial> GetCourseTutorials(String courseTitle, DataContainer dataContainer){
        ArrayList<Tutorial> tutorialList = new ArrayList<>();
        for(Course course:dataContainer.getCourseList()){
            if(courseTitle.equals(course.GetCourseTitle()))
                tutorialList = course.GetTutorialList();

        }
        return tutorialList;
    }

    public ArrayList<Lab> GetCourseLabs(String courseTitle, DataContainer dataContainer){
        ArrayList<Lab> labList = new ArrayList<>();
        for(Course course:dataContainer.getCourseList()){
            if(courseTitle.equals(course.GetCourseTitle()))
                labList = course.GetLabList();

        }
        return labList;
    }



    public static String getNameFromMatric (String marticNumber,DataContainer dataContainer) {
        try{
            for(Student currStudent:dataContainer.getStudentsList()) {
                if(currStudent.getMatricNumber().equals(marticNumber))
                    return currStudent.GetStudentName();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "Default";
    }


    public static void printStudentListByTutorial(String courseName, String tutGroupName, DataContainer dataContainer) throws IOException {
        for(Course currCourse:dataContainer.getCourseList())
        {
            if(currCourse.GetCourseTitle().equals(courseName.toUpperCase()))
            {
                for (Tutorial currTut: currCourse.GetTutorialList()){
                    System.out.println("Students registered in " + tutGroupName +": ")
                    {
                        for ( currTut: currCourse.GetTutorialList()){
                    }
                }
                return;
            }
        }


    public static void PrintTutorialVacancy(String courseTitle, DataContainer dataContainer) throws IOException, TutorialLabNotAvailableException {
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
    public static void PrintLabVacancy(String courseTitle, DataContainer dataContainer) throws IOException, TutorialLabNotAvailableException{
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

    public boolean CourseHaveVacancy(String courseTitle, DataContainer dataContainer){
        ArrayList<Course> courseList = dataContainer.getCourseList();
        Course thisCourse=null;
        try {
            for (Course course : courseList) {
                if (courseTitle.equals(course.GetCourseTitle())) {
                    thisCourse=course;
                }
            }

            return true;
        }
        catch (CourseNotFoundException e){System.out.println(e.getMessage());}
    }



}
