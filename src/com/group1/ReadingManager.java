package com.group1;

import Exceptions.TutorialLabNotAvailableException;

import java.io.IOException;
import java.util.ArrayList;

public class ReadingManager
{


    public static void printStudentListByLecture(String courseName, DataContainer dataContainer) throws IOException {
        for (Course currCourse : dataContainer.getCourseList()) {
            if (currCourse.getCourseTitle().equals(courseName.toUpperCase())) {
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
            if (currCourse.getCourseTitle().equals(courseName.toUpperCase())) {
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
            if (currCourse.getCourseTitle().equals(courseName.toUpperCase())) {
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

    public static boolean CheckStudentRegisteredForCourse(Student thisStudent,Course course){

        ArrayList<Student> studentList = course.GetStudentList();

        for(Student student:studentList){
            if(thisStudent.getMatricNumber().equals(student.getMatricNumber()))
                return true;

        }
        return false;
    }


    public static void PrintTutorialVacancy(String courseTitle, DataContainer dataContainer) throws IOException, TutorialLabNotAvailableException {
        ArrayList<Tutorial> tutorialList = new ArrayList<>();
        ArrayList<Course> courseList = dataContainer.getCourseList();
        try {
            for (Course course : courseList) {
                if (courseTitle.equals(course.getCourseTitle())) {
                    if (course.HaveTutorial() == false)
                        throw new TutorialLabNotAvailableException();
                    else tutorialList = course.GetTutorialList();

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
                if (courseTitle.equals(course.getCourseTitle())) {
                    if (course.HaveLab() == false)
                        throw new TutorialLabNotAvailableException();
                    else labList= course.GetLabList();
                break;
                }
            }

            System.out.println("The Vacancy of Lab Groups of " + courseTitle + " is as following: ");

            for (Lab lab : labList) {
                System.out.println(lab.sessionName + " " + lab.GetRegisteredStudent().size() + "/" + lab.totalVacancy);


            }
        }
        catch (TutorialLabNotAvailableException e){System.out.println(e.getMessage());}
    }

    public static boolean CourseHaveVacancy(String courseTitle, DataContainer dataContainer){
        boolean haveVacancy = false;
        ArrayList<Course> courseList = dataContainer.getCourseList();
        Course thisCourse=null;
        for (Course course : courseList) {
            if (courseTitle.equals(course.getCourseTitle())) {
                thisCourse=course;
                break;
            }
        }
        if(!thisCourse.HaveTutorial()) return true;

        ArrayList<Tutorial> tutorialList = thisCourse.GetTutorialList();
        for(Tutorial tutorial:tutorialList){
            if(tutorial.HaveVacancy())
                haveVacancy = true;
        }

        return haveVacancy;

    }



}
