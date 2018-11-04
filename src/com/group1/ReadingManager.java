package com.group1;

import Exceptions.TutorialLabNotAvailableException;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadingManager
{

/*
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
*/


    public static void printStudentListByLecture(String courseName, DataContainer dataContainer) throws IOException {
        for (Course currCourse : dataContainer.getCourseList()) {
            if (currCourse.GetCourseTitle().equals(courseName.toUpperCase())) {
                System.out.println("Students registered in " + courseName + " : ");
                for (Tutorial currTut : currCourse.GetTutorialList()) {
                    {
                        for (Student currStudent : currTut.getStudentList()) {
                            System.out.println(currStudent.GetStudentName() + " : " + currStudent.getMatricNumber());
                        }
                    }
                }
                return;
            }
        }
    }

    public static void printStudentListByTutorial(String courseName, String tutGroupName, DataContainer dataContainer) throws IOException {
        for (Course currCourse : dataContainer.getCourseList()) {
            if (currCourse.GetCourseTitle().equals(courseName.toUpperCase())) {
                for (Tutorial currTut : currCourse.GetTutorialList()) {
                    System.out.println("Students registered in " + tutGroupName + " : ");
                    {
                        for (Student currStudent : currTut.getStudentList()) {
                            System.out.println(currStudent.GetStudentName() + " : " + currStudent.getMatricNumber());
                        }
                        return;
                    }
                }

            }
        }
    }

    public static void printStudentListByLab(String courseName, String labGroupName, DataContainer dataContainer) throws IOException {
        for (Course currCourse : dataContainer.getCourseList()) {
            if (currCourse.GetCourseTitle().equals(courseName.toUpperCase())) {
                for (Lab currLab : currCourse.GetLabList()) {
                    System.out.println("Students registered in " + labGroupName + " : ");
                    {
                        for (Student currStudent : currLab.getStudentList()) {
                            System.out.println(currStudent.GetStudentName() + " : " + currStudent.getMatricNumber());
                        }
                        return;
                    }
                }
            }
        }
    }
    public static void PrintTutorialVacancy(String courseTitle, DataContainer dataContainer) throws IOException, TutorialLabNotAvailableException {
        ArrayList<Tutorial> tutorialList = new ArrayList<>();
        ArrayList<Course> courseList = dataContainer.getCourseList();
        try {
            for (Course course : courseList) {
                if (courseTitle.equals(course.GetCourseTitle())) {
                    if (course.HaveTutorial() == false)
                        throw new TutorialLabNotAvailableException();
                    else tutorialList = course.GetTutorialList();

                }
            }
        }
    }

            System.out.println("The Vacancy of Tutorial Groups of " + courseTitle + " is as following: ");

            for (Tutorial tutorial : tutorialList) {
                System.out.println(tutorial.sessionName + " " + tutorial.GetRegisteredStudent().size() + "/" + tutorial.totalVacancy);


            }
        }
        catch (TutorialLabNotAvailableException e){System.out.println(e.getMessage());}
    }



    public static void PrintLabVacancy(String courseTitle, DataContainer dataContainer) throws IOException, TutorialLabNotAvailableException {
        ArrayList<Lab> labList = new ArrayList<>();
        ArrayList<Course> courseList = dataContainer.getCourseList();
        try {
            for (Course course : courseList) {
                if (courseTitle.equals(course.GetCourseTitle())) {
                    if (course.HaveLab() == false)
                        throw new TutorialLabNotAvailableException();
                    else labList= course.GetLabList();

                }
            }

            System.out.println("The Vacancy of Lab Groups of " + courseTitle + " is as following: ");

            for (Lab lab : labList) {
                System.out.println(lab.sessionName + " " + lab.GetRegisteredStudent().size() + "/" + lab.totalVacancy);


            }
        }
        catch (TutorialLabNotAvailableException e){System.out.println(e.getMessage());}
    }
}
