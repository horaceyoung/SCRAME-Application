package com.group1;

import Exceptions.TutorialLabNotAvailableException;

import java.io.IOException;
import java.util.ArrayList;

public class ReadingManager
{


    public static void printStudentList(Course course) throws IOException {
        if(course.GetStudentList().isEmpty()){
            System.out.println(course.getCourseTitle() + " currently has no student enrolled in.");
            return;
        }


        System.out.println("Students registered in " + course.getCourseTitle() + " : ");
        ArrayList<Student> studentList = course.GetStudentList();
        for(Student student : studentList){
            System.out.println(student.getMatricNumber() + student.GetStudentName());
        }
    }



    public static void printStudentList(Tutorial tutorial) throws IOException {
        if(tutorial.GetRegisteredStudent().isEmpty()){
            System.out.println(tutorial.sessionName+ " currently has no student enrolled in.");
            return;
        }
        System.out.println("Students registered in tutorial group " + tutorial.sessionName + " : ");
        ArrayList<Student> studentList = tutorial.getStudentList();
        for(Student student : studentList){
            System.out.println(student.getMatricNumber() + student.GetStudentName());
        }
    }

    public static void printStudentList(Lab lab) throws IOException {
        if(lab.GetRegisteredStudent().isEmpty()){
            System.out.println(lab.sessionName+ " currently has no student enrolled in.");
            return;
        }


        System.out.println("Students registered in lab group" + lab.sessionName + " : ");
        ArrayList<Student> studentList = lab.getStudentList();
        for(Student student : studentList){
            System.out.println(student.getMatricNumber() + student.GetStudentName());
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
