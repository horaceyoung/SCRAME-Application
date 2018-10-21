package com.group1;

import Exceptions.CourseNameNotValidException;
import Exceptions.WeightageNotValidException;
import Exceptions.WeightageSumNotValidException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseManager {

    public static Course AddCourse(){
        System.out.println("Add Course: Please input the course title of the course. The course title shall only contain alphabets and numbers, not spaces.");
        Scanner in = new Scanner(System.in);
        String courseTitle = "Default";
        boolean titleValid = false;
        while(!titleValid) {
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

    public static void AddCourseComponent(String courtsetitle)
    {


        String[] component = {"Exam", "Coursework"};
        Course newcourse6 = new Course(courtsetitle);
        while (true)
        {

            for (String compname: component)
            {
                newcourse6.AssignComponentWeightage(newcourse6.GetComponents(), compname);
            }
            float weightagesum = 0;
            for(AssessmentComponent componentnew : newcourse6.GetComponents())
            {
                 weightagesum += componentnew.getWeightage();
            }
            if (InputManager.ValidateWeightageSum(weightagesum))
            {
                break;
            }
            else
            {
                System.out.println("The total weightage you have entered is not valid. It should sum up to 1\n");
            }
        }


        while (true)
        {
            String temp;
            while (true)
            {
                System.out.println("Do we have sub-component for coursework? Yes: Enter 1  No: Enter 0");
                Scanner ynscanner = new Scanner(System.in);
                temp = ynscanner.nextLine();
                if (InputManager.ValidateNumberInput(temp))
                {
                    break;

                }
                else
                {
                    System.out.println("Input not valid, please enter 0 or 1\n");
                }


            }

            if(Integer.parseInt(temp) == 0)
            {
                break;
            }

            while (true)
            {
                System.out.println("How many sub-components do u have?");
                Scanner numscanner = new Scanner(System.in);
                temp = numscanner.nextLine();
                if (InputManager.ValidateNumberInput(temp) && Integer.parseInt(temp) > 1)
                {
                    break;

                }
                else
                {
                    System.out.println("Input not valid, please enter number greater than 1.\n");
                }
            }
            int subcomnum = Integer.parseInt(temp);
            String[] subcoponent = new String[subcomnum];

            for (int x = 1; x <= subcomnum; x++)
            {
                System.out.println("Please enter sub-component "+ x +" name");
                Scanner subcomponentscanner = new Scanner(System.in);
                subcoponent[x-1] = subcomponentscanner.nextLine();






            }
            while (true)
            {
                for (String subcomname : subcoponent)
                {
                    newcourse6.AssignComponentWeightage(newcourse6.GetSubComponents(), subcomname);
                }
                float subweightagesum = 0;
                for(AssessmentComponent componentnew : newcourse6.GetSubComponents())
                {
                    subweightagesum += componentnew.getWeightage();
                }
                if (InputManager.ValidateWeightageSum(subweightagesum))
                {
                    break;
                }
                else
                {
                    System.out.println(subweightagesum);
                    System.out.println("The total weightage you have entered is not valid. It should sum up to 1\n");
                }
            }
            break;



        }

        FileOutputManager.WriteComponents(newcourse6);

    }

    
    public static ArrayList<String> printStudentListByTutorial(String courseTitle, String tutTitle) throws IOException {

        //Read tutorial.txt
        //Find same course name and same tutorial title

        File courseFile = new File("data/Tutorials.txt");
        Scanner tutScanner = new Scanner(courseFile);
        String[] tutorials = null;
        String studentID = "";
        String name = "";
        ArrayList<String> nameList = new ArrayList<>();

        while(tutScanner.hasNext()) {
            tutorials = tutScanner.nextLine().split("\t");
            try {
                if (tutorials[0].toUpperCase().equals(courseTitle.toUpperCase()) && tutorials[1].toUpperCase().equals(tutTitle.toUpperCase())) {
                    for (int i = 3; i <tutorials.length; i++){
                        studentID = tutorials[i];
                        name = Student.getNameFromID(studentID);
                        nameList.add(name);
                    }
                }
            } catch (Exception e){
                //
            }
        }
        return nameList;
    }

    public static ArrayList<String> printStudentListByLab(String courseTitle, String labTitle) throws IOException {

        //Read lab.txt
        //Find same course name and same tutTitle

        File courseFile = new File("data/Labs.txt");
        Scanner labScanner = new Scanner(courseFile);
        String[] labs = null;
        String studentID = "";
        String name = "";
        ArrayList<String> nameList = new ArrayList<>();

        while(labScanner.hasNext()) {
            labs = labScanner.nextLine().split("\t");
            try {
                if (labs[0].toUpperCase().equals(courseTitle.toUpperCase()) && labs[1].toUpperCase().equals(labTitle.toUpperCase())) {
                    for (int i = 3; i <labs.length; i++){
                        studentID = labs[i];
                        name = Student.getNameFromID(studentID);
                        nameList.add(name);
                        System.out.println("nameList: " + nameList);
                    }
                }
            } catch (Exception e){
                //
            }
        }
        return nameList;
    }

    public static HashMap<String, ArrayList<String>> printStudentListByLecture(String courseTitle) throws IOException {

        //Read tutorial.txt
        //Find same course name and print all students in each tutorial

        File courseFile = new File("data/Tutorials.txt");
        Scanner labScanner = new Scanner(courseFile);
        String[] lines = null;
        String tutTitle = "";
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> tutorialList = new ArrayList<>();

        while(labScanner.hasNext()) {
            lines = labScanner.nextLine().split("\t");
            try {
                if (lines[0].toUpperCase().equals(courseTitle.toUpperCase()) ) {
                    tutTitle = lines[1];
                    tutorialList.add(tutTitle);
                }
            } catch (Exception e){
                //
            }
        }

        HashMap<String, ArrayList<String>> tutAndStudent = new HashMap<>();
        for(int i = 0; i < tutorialList.size(); i++) {
            tutTitle = tutorialList.get(i);
            tutAndStudent.put(tutTitle, printStudentListByTutorial(courseTitle, tutorialList.get(i)));
        }
        return tutAndStudent;
    }


}
