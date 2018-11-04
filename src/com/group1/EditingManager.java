package com.group1;

import Exceptions.LabGroupNonExistentException;
import Exceptions.TutorialGroupNonExistentException;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;
import java.util.Map;
import java.util.Scanner;

public class EditingManager
{
    public void RegisterStudentToCourseLecture(Student thisStudent, String courseTitle, DataContainer dataContainer) {
        ArrayList<Course> courseList = dataContainer.getCourseList();
        ArrayList<Student> courseStudentList = new ArrayList<>();

        for (Course course : courseList) {
            if (courseTitle.equals(course.GetCourseTitle())) {
                courseStudentList=course.GetStudentList();
                courseStudentList.add(thisStudent);
            }
        }

        ArrayList<HashMap<String,ArrayList<AssessmentComponent>>> studentCourseList = thisStudent.GetCourseAndResult();
        HashMap<String, ArrayList<AssessmentComponent>> newCourseAdded = new HashMap();
        newCourseAdded.put(courseTitle,null);
        studentCourseList.add(newCourseAdded);


        System.out.println("Student "+thisStudent.GetMarticNumber()+" "+thisStudent.GetStudentName()+" has been registered to Course "+courseTitle);
    }

    public void RegisterStudentToTutorial(Student student, String courseTitle, String tutorialName, DataContainer dataContainer){

        ArrayList<Course> courseList = dataContainer.getCourseList();
        ArrayList<Tutorial> tutorialList = new ArrayList<>();
        Tutorial thisTutorial=null;

        for (Course course : courseList) {
            if (courseTitle.equals(course.GetCourseTitle())) {
                tutorialList = course.GetTutorialList();
            }
        }
        try{
        for(Tutorial tutorial:tutorialList){
            if(tutorialName.equals(tutorial.sessionName)){
                thisTutorial = tutorial;
            }
            else throw new TutorialGroupNonExistentException();
        }

        thisTutorial.GetRegisteredStudent().add(student);

         System.out.println("Student "+student.GetMarticNumber()+" "+student.GetStudentName()+" has been registered to "+ tutorialName+ "of course "+courseTitle);

        }
        catch (TutorialGroupNonExistentException e){
            System.out.println(e.getMessage());
        }
    }

    public void RegisterStudentToLab(Student student, String courseTitle, String labName, DataContainer dataContainer){

        ArrayList<Course> courseList = dataContainer.getCourseList();
        ArrayList<Lab> labList = new ArrayList<>();
        Lab thisLab=null;

        for (Course course : courseList) {
            if (courseTitle.equals(course.GetCourseTitle())) {
                labList = course.GetLabList();
            }
        }
        try{
            for(Lab lab:labList){
                if(labName.equals(lab.sessionName)){
                    thisLab = lab;
                }
                else throw new LabGroupNonExistentException();
            }

            thisLab.GetRegisteredStudent().add(student);

            System.out.println("Student "+student.GetMarticNumber()+" "+student.GetStudentName()+" has been registered to "+ labName+ "of course "+courseTitle);

        }
        catch (LabGroupNonExistentException e){
            System.out.println(e.getMessage());
        }
    }


    public static void AddCourseComponent(String courtsetitle, DataContainer container) {

        Course newcourse6 = null;
        for ( Course course:container.getCourseList()){
            if (course.GetCourseTitle().equals(courtsetitle))
                newcourse6 = course;
        }

        if (!newcourse6.GetComponents().isEmpty())
        {
            System.out.println("This course's component weightage has already been assigned.");
            return;
        }

        String[] component = {"Exam", "Coursework"};

        while (true) {
            for (String compname : component) {
                newcourse6.AssignComponentWeightage(newcourse6.GetComponents(), compname);
            }
            float weightagesum = 0;
            for (AssessmentComponent componentnew : newcourse6.GetComponents()) {
                weightagesum += componentnew.getWeightage();
            }
            System.out.println(weightagesum);
            if (Validation.ValidateWeightageSum(weightagesum)) {
                break;
            } else {
                System.out.println("The total weightage you have entered is not valid. It should sum up to 1\n");
                newcourse6.ClearComponentWeightage(newcourse6.GetComponents());

            }
        }


        while (true) {
            String temp;
            while (true) {
                System.out.println("Do we have sub-component for coursework? Yes: Enter 1  No: Enter 0");
                Scanner ynscanner = new Scanner(System.in);
                temp = ynscanner.nextLine();
                if (Validation.ValidateNumberInput(temp)) {
                    break;

                } else {
                    System.out.println("Input not valid, please enter 0 or 1\n");
                }


            }

            if (Integer.parseInt(temp) == 0) {
                break;
            }

            while (true) {
                System.out.println("How many sub-components do u have?");
                Scanner numscanner = new Scanner(System.in);
                temp = numscanner.nextLine();
                if (Validation.ValidateNumberInput(temp) && Integer.parseInt(temp) > 1) {
                    break;

                } else {
                    System.out.println("Input not valid, please enter number greater than 1.\n");
                }
            }
            int subcomnum = Integer.parseInt(temp);
            String[] subcoponent = new String[subcomnum];

            for (int x = 1; x <= subcomnum; x++) {
                System.out.println("Please enter sub-component " + x + " name");
                Scanner subcomponentscanner = new Scanner(System.in);
                subcoponent[x - 1] = subcomponentscanner.nextLine();


            }
            while (true) {
                for (String subcomname : subcoponent) {
                    newcourse6.AssignComponentWeightage(newcourse6.GetSubComponents(), subcomname);
                }
                float subweightagesum = 0;
                for (AssessmentComponent componentnew : newcourse6.GetSubComponents()) {
                    subweightagesum += componentnew.getWeightage();
                }
                if (Validation.ValidateWeightageSum(subweightagesum)) {
                    break;
                } else {
                    System.out.println(subweightagesum);
                    System.out.println("The total weightage you have entered is not valid. It should sum up to 1\n");
                    newcourse6.ClearComponentWeightage(newcourse6.GetSubComponents());
                }
            }
            break;


        }



    }

    public static void printTranscript(String studentMatricTranscript, DataContainer dataContainer) throws FileNotFoundException{

        String transcriptOutcome = "Matric No: " + studentMatricTranscript + "\n";
        HashMap<String, ArrayList<AssessmentComponent>> courseAndResult;
        HashMap<String, ArrayList<AssessmentComponent>> currentCourse;
        //read Results file to get results
        for(Student student : dataContainer.getStudentsList()) {
            if (student.GetMarticNumber().equals(studentMatricTranscript)){
                transcriptOutcome += "Student Name: " + student.GetStudentName() + "\n";
            }

            courseAndResult = student.GetCourseAndResult();
            for (String key : courseAndResult.keySet()){
                    transcriptOutcome += key + "\n" + "Overall Mark: " + "\n";
                    ArrayList<AssessmentComponent> components = courseAndResult.get(key);
                    for (AssessmentComponent component : components){
                        transcriptOutcome += "\t" + component.getAssessmentType() + " " + component.getWeightage() + " " + component.getResult() + "\n";
                    }
            }



        }
        System.out.println(transcriptOutcome);
    }
}
