package com.group1;

import Exceptions.CourseNameNotValidException;
import Exceptions.WeightageNotValidException;
import Exceptions.WeightageSumNotValidException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class CourseManager {

    public static Course AddCourse() {
        System.out.println("Add Course: Please input the course title of the course. The course title shall only contain alphabets and numbers, not spaces.");
        Scanner in = new Scanner(System.in);
        String courseTitle = "Default";
        boolean titleValid = false;
        while (!titleValid) {
            courseTitle = in.nextLine();
            try {
                if (!InputManager.ValidateGroupNameInput(courseTitle)) {
                    throw new CourseNameNotValidException();
                }
                titleValid = true;
                System.out.println("Create Course Success: The course with course title " + courseTitle + " has been successfully created");

            } catch (CourseNameNotValidException e) {
                System.out.println(e.getMessage());
            }
        }
        return new Course(courseTitle);
    }

    public static void AddCourseComponent(String courtsetitle) {


        String[] component = {"Exam", "Coursework"};
        Course newcourse6 = new Course(courtsetitle);
        while (true) {
            for (String compname : component) {
                newcourse6.AssignComponentWeightage(newcourse6.GetComponents(), compname);
            }
            float weightagesum = 0;
            for (AssessmentComponent componentnew : newcourse6.GetComponents()) {
                weightagesum += componentnew.getWeightage();
                System.out.println(weightagesum);
            }
            if (InputManager.ValidateWeightageSum(weightagesum)) {
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
                if (InputManager.ValidateNumberInput(temp)) {
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
                if (InputManager.ValidateNumberInput(temp) && Integer.parseInt(temp) > 1) {
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
                if (InputManager.ValidateWeightageSum(subweightagesum)) {
                    break;
                } else {
                    System.out.println(subweightagesum);
                    System.out.println("The total weightage you have entered is not valid. It should sum up to 1\n");
                    newcourse6.ClearComponentWeightage(newcourse6.GetSubComponents());
                }
            }
            break;


        }

        FileOutputManager.WriteComponents(newcourse6);

    }


    public static void printStudentListByLecture(String courseName) throws IOException {
        File tutFile = new File("data/Tutorials.txt");
        Scanner tutScanner = new Scanner(tutFile);
        boolean hasStudent = false;
        String studentName;
        while (tutScanner.hasNext()) {
            String[] tutorialGroup = tutScanner.nextLine().split("\t");
            if (tutorialGroup[0].toUpperCase().equals(courseName.toUpperCase())) {
                for (int i = 3; i < tutorialGroup.length; i++) {
                    studentName = Student.getNameFromMatric(tutorialGroup[i]);
                    System.out.println(studentName + ", ");
                    hasStudent = true;
                }
            } else {
                if (hasStudent == false)
                    System.out.println("Currently There's no student registered in " + courseName);
                break;
            }
        }
    }

    public static void printStudentListByTutorial(String courseName) throws IOException {
        File tutFile = new File("data/Tutorials.txt");
        String studentName;
        Scanner tutScanner = new Scanner(tutFile);
        while (tutScanner.hasNext()) {
            String[] tutorialGroup = tutScanner.nextLine().split("\t");

            if (tutorialGroup[0].toUpperCase().equals(courseName.toUpperCase())) {

                System.out.println(tutorialGroup[1] + "Students List: ");
                boolean hasStudent = false;

                for (int i = 3; i < tutorialGroup.length; i++) {

                    studentName = Student.getNameFromMatric(tutorialGroup[i]);
                    System.out.println(studentName + "   ");
                    hasStudent = true;

                }
                if (hasStudent == false)
                    System.out.println("Currently There's no student registered in " + tutorialGroup[1]);
            } else
                break;


        }
    }

    public static void printStudentListByLab(String courseName) throws IOException {
        File labFile = new File("data/Labs.txt");

        Scanner labScanner = new Scanner(labFile);
        while (labScanner.hasNext()) {
            String[] labGroup = labScanner.nextLine().split("\t");
            String studentName;
            if (labGroup[0].toUpperCase().equals(courseName.toUpperCase())) {

                System.out.println(labGroup[1] + "  Students List: ");
                boolean hasStudent = false;

                for (int i = 3; i < labGroup.length; i++) {

                    studentName = Student.getNameFromMatric(labGroup[i]);
                    System.out.println(studentName + "   ");
                    hasStudent = true;

                }
                if (hasStudent == false)
                    System.out.println("Currently There's no student registered in " + labGroup[1]);
            } else
                break;


        }
    }




}
