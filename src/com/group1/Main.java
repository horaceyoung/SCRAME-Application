package com.group1;

import Exceptions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
	// write your code here
        String studentMatric;
        String courseTitle;
        String tutorialName;
        String labName;
        Course newCourse = null;
        Student newStudent = null;
        Scanner in = new Scanner(System.in);
        DataContainer dataContainer = new DataContainer();
        ArrayList<Tutorial> tutorialList = new ArrayList<>();
        ArrayList<Lab> labList = new ArrayList<>();


        // Initialization and deserialize the data container file
        System.out.println("Initializing...");
        try
        {
            FileInputStream fileIn = new FileInputStream("data/SerializableDataContainer.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            dataContainer = (DataContainer) objectIn.readObject();
            dataContainer.professors.add(new Professor("Li Fang", "SCSE"));
            dataContainer.professors.add(new Professor("Jack", "SCSE"));
            dataContainer.professors.add(new Professor("John", "SCSE"));
            dataContainer.professors.add(new Professor("Peter", "SCSE"));

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }





        String intro =    "|===================================================|" + "\n"
                        + "|      Welcome to the SCRAME application console:   |" + "\n"
                        + "|        Press the corresponding number to use:     |" + "\n"
                        + "|===================================================|" + "\n"
                        + "| 1: Add a new student                              |" + "\n"
                        + "| 2: Add a new course                               |" + "\n"
                        + "| 3: Register student for a course                  |" + "\n"
                        + "| 4: Check available slot in a class                |" + "\n"
                        + "| 5: Print student list by lecture, tutorial or lab |" + "\n"
                        + "| 6. Enter course assessment components weightage   |" + "\n"
                        + "| 7: Enter exam marks for students                  |" + "\n"
                        + "| 8: Enter coursework marks for students            |" + "\n"
                        + "| 9. Print course statistics                        |" + "\n"
                        + "| 10. Print student transcript                      |" + "\n"
                        + "|===================================================|" + "\n"
                        + "| 0. Save and quit                                  |" + "\n"
                        + "|===================================================|" + "\n";


        int choice = 0;
        while(choice!=11)
        {
            System.out.println(intro);
            choice = in.nextInt();
            in.nextLine();




            switch (choice){
                case 1:
		            //Testcase 1: Add in student
                    dataContainer.AddStudent();
                    break;
                case 2:
                    // Testcase 2: Create the course
                    dataContainer.AddCourse();
                    break;
                case 3:
                // Testcase 3: Register student for a course
                   EditingManager.Register(dataContainer);
                    break;
                case 4:
                // Testcase 4: Check available slot in a class
                    ReadingManager.PrintVacancy(dataContainer);
                    break;
                case 5:
                //Testcase 5: Print student list by lecture, tutorial or lab
                    System.out.println("Please key in Course Code");
                    Scanner sc = new Scanner(System.in);
                    String courseName = sc.nextLine();
                    while (true) {
                        try{
                            if (!Validation.CheckCourseExisted(courseName.toUpperCase(),dataContainer))
                                System.out.println("The course you entered does not exist. Please add this course first.\n");

                            else{
                                ArrayList<Course> courseList = dataContainer.getCourseList();
                                for(Course course:courseList){
                                    if(courseName.equals(course.getCourseTitle())){
                                        newCourse=course;
                                        break;
                                    }
                                }


                                System.out.println("Key in 'Lec' to print by lecture || 'Tut' to print by tutorial || 'Lab' to print by lab || '0' to exit");
                                String printList = sc.nextLine().toUpperCase();

                                if(printList.equals("LEC")){
                                    ReadingManager.printStudentList(newCourse);
                                    break;
                                }
                                else if(printList.equals("TUT")) {
                                    System.out.println("Please type the name of a tutorial to check students enrolled in: ");
                                    int i = 1;
                                    while (i <= newCourse.GetTutorialList().size()) {
                                        System.out.println(i + ". " + newCourse.GetTutorialList().get(i - 1).GetName());
                                        i++;
                                    }
                                    tutorialName = in.nextLine();


                                    tutorialList = newCourse.GetTutorialList();
                                    Tutorial thisTutorial=null;
                                    boolean found=false;

                                    for(Tutorial tutorial:tutorialList){
                                        if(tutorialName.equals(tutorial.GetName())){
                                            thisTutorial = tutorial;
                                            found=true;
                                            }
                                            }if (found==false) throw new TutorialGroupNonExistentException();



                                    ReadingManager.printStudentList(thisTutorial);
                                    break;
                                }
                                else if(printList.equals("LAB")) {
                                    System.out.println("Please type the name of a lab to check students enrolled in: ");
                                    int i = 1;
                                    while (i <= newCourse.GetLabList().size()) {
                                        System.out.println(i + ". " + newCourse.GetLabList().get(i - 1).GetName());
                                        i++;
                                    }
                                    labName = in.nextLine();


                                    labList = newCourse.GetLabList();
                                    Lab thisLab = null;
                                    boolean found=false;

                                    for(Lab lab:labList){
                                        if(labName.equals(lab.GetName())){
                                            thisLab = lab;
                                            found=true;
                                        }
                                    }
                                        if (found==false) throw new LabGroupNonExistentException();


                                    ReadingManager.printStudentList(thisLab);
                                    break;
                                }else if(printList.equals("0")) break;
                                else
                                 System.out.println("Invalid option.");

                            }
                        }
                        catch (TutorialGroupNonExistentException e){System.out.println(e.getMessage());}
                        catch (LabGroupNonExistentException e){System.out.println((e.getMessage()));}
                        catch (IOException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        }
                    break;
                case 6:
                //Enter component weightage
                    System.out.println("Adding course assessment weightage...\n" +
                                       "Please input the course title:");
                    Scanner scanner6 = new Scanner(System.in);
                    String title6 = scanner6.nextLine();
                    try
                    {
                        if (!Validation.CheckCourseExisted(title6, dataContainer))
                        {
                            System.out.println("The course you entered does not exist. Please add this course first.\n");
                        }
                        else
                        {
                            EditingManager.AddCourseComponent(title6, dataContainer);
                        }
                    }
                    catch (Exception e)
                    {
                    System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                // Testcase 7: Enter exam mark
                    System.out.println("Please enter the course title:");
                    Scanner scanner = new Scanner(System.in);
                    String title7 = scanner.next();
                    try {
                        if (Validation.CheckCourseExisted(title7, dataContainer)) {
                            System.out.println("Please enter the student's matriculation number:");
                            String matric = scanner.next();
                            if(Validation.studentExists(matric, dataContainer)) {
                                EditingManager.AssignExamResults(matric, title7, dataContainer); }
                            else { throw new StudentNotExistException(); }
                        } else { throw new CourseNotFoundException(); }
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                        scanner.nextLine();
                    }
                    break;
                case 8:
                    // Testcase 8: Enter coursework mark
                    System.out.println("Please enter the course title:");
                    Scanner scanner8 = new Scanner(System.in);
                    String title8 = scanner8.next();
                    try {
                        if (Validation.CheckCourseExisted(title8, dataContainer)) {
                            System.out.println("Please enter the student's matriculation number:");
                            String matric = scanner8.next();
                            if(Validation.studentExists(matric, dataContainer)) {
                                EditingManager.AssignCourseworkResults(matric, title8, dataContainer); }
                            else { throw new StudentNotExistException(); }
                        } else { throw new CourseNotFoundException(); }
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                        scanner8.nextLine();
                    }
                    break;



                case 9:

                 //testcase 8: Print course statistics
                 //Show grade percentage for overall (exam + coursework)
                 //exam only and coursework only.
                    System.out.println("Please enter Course Code to check for course statistics");
                    Scanner sc1 = new Scanner(System.in);
                    String coursetitle = sc1.nextLine().toUpperCase();
                        if (!Validation.CheckCourseExisted(coursetitle, dataContainer))
                            System.out.println("The course you entered does not exist. Please enter another course code.\n");
                        else{
                            ReadingManager.printCourseStatistics(coursetitle, dataContainer);
                         }
                        break;

            case 10:
            	//test case 9: Print student transcript.
            	//individual overall course mark and grade for all the courses registered
            	//individual component marks ¨C exam, coursework, subcomponents from Result.txt 
            	//The configured weightages should be displayed as well
            	System.out.println("Please enter the Student Matric Number to check for transcript");
            	Scanner sc2 = new Scanner(System.in);
            	String studentMatricTranscript = sc2.nextLine();
            	if (!Validation.studentExists(studentMatricTranscript, dataContainer)) {
            			 System.out.println("The Matric Number does not exist.\n");
            	}
            	else {
            			ReadingManager.printTranscript(studentMatricTranscript, dataContainer);
            	}
                break;

                case 0:
                    try{
                        FileOutputStream fileOut = new FileOutputStream("data/SerializableDataContainer.ser");
                        ObjectOutputStream out = new ObjectOutputStream(fileOut);
                        out.writeObject(dataContainer);
                        out.close();
                        fileOut.close();
                        System.out.println("The data has been successfully saved");

                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
            }
        }
    }
}
