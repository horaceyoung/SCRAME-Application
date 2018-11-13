package com.group1;

import Exceptions.CourseNotFoundException;
import Exceptions.StudentResultNotExistentException;
import Exceptions.TutorialLabNotAvailableException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ReadingManager
{

    public static void readProefessors(DataContainer dataContainer){
        String line;
        String[] Prof;
        try{
            BufferedReader profIn = new BufferedReader(new FileReader("data/Professors.txt"));
            while((line = profIn.readLine())!=null){
                Prof = line.split("\t");
                dataContainer.professors.add(new Professor(Prof[0], Prof[1]));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    public static void printStudentList(Course course) throws IOException {
        if(course.getStudentList().isEmpty()){
            System.out.println(course.getCourseTitle() + " currently has no student enrolled in.");
            return;
        }


        System.out.println("Students registered in " + course.getCourseTitle() + " : ");
        ArrayList<Student> studentList = course.getStudentList();
        for(Student student : studentList){
            System.out.println(student.getMatricNumber() + student.getName());
        }
    }



    public static void printStudentList(Tutorial tutorial) throws IOException {
        if(tutorial.getRegisteredStudent().isEmpty()){
            System.out.println(tutorial.getName()+ " currently has no student enrolled in.");
            return;
        }
        System.out.println("Students registered in tutorial group " + tutorial.getName() + " : ");
        ArrayList<Student> studentList = tutorial.getRegisteredStudent();
        for(Student student : studentList){
            System.out.println(student.getMatricNumber() + student.getName());
        }
    }

    public static void printStudentList(Lab lab) throws IOException {
        if(lab.getRegisteredStudent().isEmpty()){
            System.out.println(lab.getName()+ " currently has no student enrolled in.");
            return;
        }


        System.out.println("Students registered in lab group" + lab.getName() + " : ");
        ArrayList<Student> studentList = lab.getRegisteredStudent();
        for(Student student : studentList){
            System.out.println(student.getMatricNumber() + student.getName());
        }
    }

    public static boolean checkStudentRegisteredForCourse(Student thisStudent, Course course){

        ArrayList<Student> studentList = course.getStudentList();

        for(Student student:studentList){
            if(thisStudent.getMatricNumber().equals(student.getMatricNumber()))
                return true;

        }
        return false;
    }


    public static void printTutorialVacancy(String courseTitle, DataContainer dataContainer) throws IOException, TutorialLabNotAvailableException {
        ArrayList<Tutorial> tutorialList = new ArrayList<>();
        ArrayList<Course> courseList = dataContainer.getCourseList();
        try {
            for (Course course : courseList) {
                if (courseTitle.equals(course.getCourseTitle())) {
                    if (course.haveTutorial() == false)
                        throw new TutorialLabNotAvailableException();
                    else tutorialList = course.getTutorialList();

                }
            }



            System.out.println("The Vacancy of Tutorial Groups of " + courseTitle + " is as following: ");

            for (Tutorial tutorial : tutorialList) {
                System.out.println(tutorial.getName() + " " + tutorial.getRegisteredStudent().size() + "/" + tutorial.getTotalVacancy());


            }
        }
        catch (TutorialLabNotAvailableException e){System.out.println(e.getMessage());}
    }


    public static Course findCourse(String courseTitle, DataContainer dataContainer){
        Course newCourse = null;
        ArrayList<Course> courseList = dataContainer.getCourseList();
        for(Course course:courseList){
            if(courseTitle.equals(course.getCourseTitle())){
                newCourse=course;
                break;
            }
        }
        return newCourse;
    }

    public static void printLabVacancy(String courseTitle, DataContainer dataContainer) throws IOException, TutorialLabNotAvailableException {
        ArrayList<Lab> labList = new ArrayList<>();
        ArrayList<Course> courseList = dataContainer.getCourseList();
        try {
            for (Course course : courseList) {
                if (courseTitle.equals(course.getCourseTitle())) {
                    if (course.haveLab() == false)
                        throw new TutorialLabNotAvailableException();
                    else labList= course.getLabList();
                break;
                }
            }

            System.out.println("The Vacancy of Lab Groups of " + courseTitle + " is as following: ");

            for (Lab lab : labList) {
                System.out.println(lab.getName() + " " + lab.getRegisteredStudent().size() + "/" + lab.getTotalVacancy());


            }
        }
        catch (TutorialLabNotAvailableException e){System.out.println(e.getMessage());}
    }

    public static boolean courseHaveVacancy(String courseTitle, DataContainer dataContainer){
        boolean haveVacancy = false;
        ArrayList<Course> courseList = dataContainer.getCourseList();
        Course thisCourse=null;
        for (Course course : courseList) {
            if (courseTitle.equals(course.getCourseTitle())) {
                thisCourse=course;
                break;
            }
        }
        if(!thisCourse.haveTutorial()) return true;

        ArrayList<Tutorial> tutorialList = thisCourse.getTutorialList();
        for(Tutorial tutorial:tutorialList){
            if(tutorial.haveVacancy())
                haveVacancy = true;
        }

        return haveVacancy;

    }
    
    public static void printTranscript(String studentMatricTranscript, DataContainer dataContainer){

        String transcriptOutcome = "Matric No: " + studentMatricTranscript + "\n";
        HashMap<String, ArrayList<AssessmentComponent>> courseAndResult;
        HashMap<String, ArrayList<AssessmentComponent>> currentCourse;
        int count = 2;
        float overallMark = 0;
        //read Results file to get results
        for(Student student : dataContainer.getStudentsList()) {
            if (student.getMatricNumber().toUpperCase().equals(studentMatricTranscript.toUpperCase())) {
                transcriptOutcome += "Student Name: " + student.getName() + "\n";

                courseAndResult = student.getCourseAndResult();
                for (String key : courseAndResult.keySet()) {
                    transcriptOutcome += key + "\n" + "Overall Mark: " + "\n";
                    ArrayList<AssessmentComponent> components = courseAndResult.get(key);
                    for (AssessmentComponent component : components) {
                        if (count > 0) {
                            overallMark += component.getWeightage() * component.getResult();
                            count--;
                        }
                        transcriptOutcome += "\t" + component.getAssessmentType() + " " + component.getWeightage() + " "
                                + component.getResult() + "\n";
                    }
                }

            }
        }transcriptOutcome += "\tOverall (Exam + Coursework): " + overallMark;
        System.out.println(transcriptOutcome);
    }
    
    
    public static void printCourseStatistics(String courseTitle, DataContainer datacontainer){
    	Course thisCourse = null;
    	ArrayList<Student> studentList = new ArrayList<>();
        for(Course course: datacontainer.getCourseList()){
            if (course.getCourseTitle().equals(courseTitle.toUpperCase())) {
            	thisCourse = course;
            	break;
            	}
        }
        studentList=thisCourse.getStudentList();
        float examResult=0;
        float courseWorkResult=0;
        HashMap<String,ArrayList<AssessmentComponent>> resultList = new HashMap<>();
        ArrayList<AssessmentComponent> result = new ArrayList<>();

        try {
            for (Student student : studentList) {
                resultList = student.getCourseAndResult();
                result = resultList.get(courseTitle);
                if (result.size()<1)
                    throw new StudentResultNotExistentException(student, thisCourse);
                examResult += result.get(0).getResult();
                courseWorkResult += result.get(1).getResult();
            }
        }catch (StudentResultNotExistentException e){System.out.println(e.getMessage());}

        int studentSize = studentList.size();
        float examAve = examResult/studentSize;
        float courseWorkAve = courseWorkResult/studentSize;
        float overallAve = examAve * result.get(0).getWeightage()+courseWorkAve*result.get(1).getWeightage();


        System.out.println("Course: " + courseTitle+"\nStatistics: Overall Percentage - "+overallAve +"\nExam Percentage - "+examAve+"\nCourse Work Percentage - "+courseWorkAve);


        }


    public static void printVacancy(DataContainer dataContainer){
        String studentMatric;
        String courseTitle;
        String tutorialName;
        String labName;
        Student newStudent = null;
        Course newCourse = null;
        while(true){
        Scanner in = new Scanner(System.in);
        System.out.println("Check Session Vacancy: Please input the course title you want to check: ");
        courseTitle = in.nextLine();

            try{
                if(Validation.checkCourseExisted(courseTitle, dataContainer)){
                    System.out.println("Check Session Vacancy: Please select the type of session you wish to check by inputting corresponding integer value:\n 1. Tutorial \n2.Lab \n");
                    String sessionChoice=in.next();
                    if(!sessionChoice.equals("1")&&!sessionChoice.equals("2"))
                    {
                        System.out.println("Please input valid choices.");
                        continue;
                    }
                    switch (sessionChoice){
                        case "1":
                            ReadingManager.printTutorialVacancy(courseTitle, dataContainer);
                            break;
                        case "2":
                            ReadingManager.printLabVacancy(courseTitle, dataContainer);
                            break;
                    }
                    break;
                }
                else {
                    throw new CourseNotFoundException();
                }

            }
            catch (TutorialLabNotAvailableException e){
                System.out.println(e.getMessage());
                continue;
            }
            catch (CourseNotFoundException e){
                System.out.println(e.getMessage());
                continue;
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                continue;
            }
        }
    }
}
