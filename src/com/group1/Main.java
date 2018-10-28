package com.group1;

import Exceptions.CourseNotFoundException;
import Exceptions.StudentNotExistException;
import Exceptions.StudentNotRegisteredForTheCourse;
import Exceptions.StudentResultAlreadyExistsException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String studentMatric;
        String courseTitle;
        String TutorialName;
        String LabName;
        Course newCourse;
        Scanner in = new Scanner(System.in);




        String intro = "Welcome to the SCRAME application console: \nPress the corresponding number to use: \n"
                + "1: Add a new student:\n"
		+ "2: Add a new course:\n"
		+ "3: Register student for a course:\n"
		+ "4: Check available slot in a class:\n"
		+ "5: Print student list by lecture, tutorial or laboratory:\n"
                + "6. Enter course assessment components weightage\n\n"
                + "7: Enter coursework mark – inclusive of its components.\n"
                + "8: Enter exam mark.\n"
                + "9. Print course statistics"
                + "10. Print student transcript.\n";


        int choice = 0;
        while(choice!=11){
            System.out.println(intro);
            choice = in.nextInt();
            in.nextLine();
            switch (choice){
                case 1:
		            //Testcase 1: Add in student
                    Student newStudent = StudentManager.AddStudent();
                    FileOutputManager.WriteStudent(newStudent.GetStudentName(),newStudent.GetMarticNumber());
                    break;
                   
			    
                case 2:
                    // Testcase 2: Create the course
                    Course newCourse1 = CourseManager.AddCourse();
                    // Assign the coordinator
                    try{
                        if(FileReadManager.CheckCourseExists(newCourse1.GetCourseTitle())){
                            System.out.println("Add Course Failed: a course with the same course title has already been added");
                        }
                        else{
                            newCourse1.AssignCoordinator();
                            FileOutputManager.WriteCourse(newCourse1);
                            // Add Labs and Tutorialss
                            newCourse1.AddTutorialLabGroups("Tutorial");
                            newCourse1.AddTutorialLabGroups("Lab");
                            FileOutputManager.WriteSessions(newCourse1);
                        }
                    }
                    catch (IOException e){
                        System.out.println(e.getMessage());
                    }

                    break;


                case 3:
                // Testcase 3: Register student for a course
                    System.out.println("Register course: Please input the matric number of the student to register: ");
                    boolean hasSessions;
                    studentMatric = in.nextLine();
                    try{
                        if(FileReadManager.CheckStudentExists(studentMatric)){
                            System.out.println("Student Info: " + FileReadManager.GetStudentInfo(studentMatric) +"  " + studentMatric);
                        }
                        else {
                            throw new StudentNotExistException();
                        }

                        System.out.println("Register course: Please input the course title you want to register with: ");
                        courseTitle = in.nextLine();

                        if(FileReadManager.CheckCourseExists(courseTitle)){

                            newCourse = new Course(courseTitle);
                            FileReadManager.GetCourseSessions(courseTitle, newCourse);
                            hasSessions = FileReadManager.GetCourseSessions(courseTitle, newCourse);

                        }
                        else {
                            throw new CourseNotFoundException();
                        }

                        while (hasSessions){
                            System.out.println("Please select a tutorial to be enrolled in:");
                            TutorialName = in.nextLine();
                            boolean tutorialFound = false;
                            boolean labFound = false;
                            for(int i = 0 ; i < newCourse.GetTutorialList().size();i++){
                                if(newCourse.GetTutorialList().get(i).GetName().equals(TutorialName)){
                                    System.out.println("Registered successfully with Tutorial " + TutorialName);
                                    tutorialFound = true;
                                }
                            }

                        if (!tutorialFound) {
                            System.out.println("TutorialNotExistException: The tutorial name entered is not in the course.");
                            continue;
                        }


                        System.out.println("Please select a lab to be enrolled in:");
                        LabName = in.nextLine();
                            for(int i = 0 ; i < newCourse.GetLabList().size();i++){
                                if(newCourse.GetLabList().get(i).GetName().equals(LabName)){
                                    System.out.println("Registered successfully with Lab " + LabName);
                                    labFound = true;
                                }
                            }

                            if (!labFound) {
                                System.out.println("TutorialNotExistException: The tutorial name entered is not in the course.");
                                continue;
                            }

                        FileOutputManager.RegisterCourse(studentMatric, courseTitle, TutorialName, LabName);
                            break;
                        }

                        FileOutputManager.RegisterCourseWithoutTutorialLab(studentMatric, courseTitle);
                        System.out.println("Course Registration Successful: The student with matric number " + studentMatric + " has been succesffully regiestered with course "
                         + courseTitle + "\n");

                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;


            case 4:
            // Testcase 4: Check available slot in a class
                System.out.println("Check Session Vacancy: Please input the course title you want to check: ");
                courseTitle = in.nextLine();
                try{
                    if(FileReadManager.CheckCourseExists(courseTitle)){
                        int sessionChoice;
                        System.out.println("Check Session Vacancy: Please select the type of session you wish to check by inputting corresponding integer value: \n 1. Tutorial \n2.Lab \n");
                        sessionChoice=in.nextInt();
                        switch (sessionChoice){
                            case 1:
                                FileReadManager.PrintTutorialVacancy(courseTitle);
                                break;
                            case 2:
                                FileReadManager.PrintLabVacancy(courseTitle);
                                break;
                        }
                    }
                    else {
                        throw new CourseNotFoundException();
                    }

                    }
                catch (Exception e){
                    System.out.println(e.getMessage());
            }
            break;
			    
			    
            case 5:
                //Testcase 5: Print student list by lecture, tutorial or lab
                        System.out.println("Please key in Course Code");
                        Scanner sc = new Scanner(System.in);
                        String courseName = sc.nextLine();
                        try{
                            if (!FileReadManager.CheckCourseExists(courseName))
                                System.out.println("The course you entered does not exist. Please add this course first.\n");

                            else{
                    System.out.println("Key in 'Lec' to print by lecture || 'Tut' to print by tutorial || 'Lab' to print by lab");
                                String printList = sc.nextLine().toUpperCase();

                                if(printList.equals("LECTURE"))
                                 CourseManager.printStudentListByLecture(courseName);

                                else if(printList.equals("TUTORIAL"))
                                 CourseManager.printStudentListByTutorial(courseName);

                                else if(printList.equals("LAB"))
                                 CourseManager.printStudentListByLab(courseName);
                                else
                                 System.out.println("Invalid option.");
                             }
                        }
                        catch (IOException e)
                        {
                            System.out.println(e.getMessage());
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
            if (!FileReadManager.CheckCourseExists(title6))
            {
            System.out.println("The course you entered does not exist. Please add this course first.\n");

            }
            else
            {
            CourseManager.AddCourseComponent(title6);
            }
            }
            catch (IOException e)
            {
            System.out.println(e.getMessage());
            }
            break;
			    
			    
			    
            case 7:
            // Testcase 7: Enter coursework mark
                System.out.println("Enter results for : Please enter the course title:");
                Scanner scanner = new Scanner(System.in);
                List<String> ComponentResultList = new ArrayList<String>();
                float courseExamGrade=0;
                float courseWorkResult =0;
                try {
                    courseName = scanner.next();
                    if (FileReadManager.CheckCourseExists(courseName)) {
                        System.out.println("Enter coursework mark: Please enter the student's matriculation number:");
                        studentMatric = scanner.next();
                        if(!FileReadManager.CheckWhetherStudentRegisteredForACourse(studentMatric,courseName))
                            throw new StudentNotRegisteredForTheCourse();
                        if(FileReadManager.CheckStudentResultsRecord(studentMatric,courseName)){
                            throw new StudentResultAlreadyExistsException();
                        }

                        System.out.println("Enter coursework mark: Please enter the student's result for exam:");
                        courseExamGrade=scanner.nextFloat();

                        ArrayList<AssessmentComponent> components = FileReadManager.GetCourseWorkComponentsList(courseName);
                        for(AssessmentComponent component:components){
                            System.out.println("Enter coursework mark: Please enter the student's mark for "+component.getAssessmentType()+" :");
                            float studentMark = scanner.nextFloat();
                            //TODO: Validate float input
                            String result = component.getAssessmentType() + " "+Float.toString(studentMark);
                            ComponentResultList.add(result);
                            courseWorkResult += studentMark * component.getWeightage();
                        }
                        String[] ComponentResults = ComponentResultList.toArray(new String[0]);


                        if(!FileReadManager.CheckStudentResultsRecord(studentMatric,courseName)){
                            FileOutputManager.WriteResults(studentMatric,courseName,Float.toString(courseExamGrade),Float.toString(courseWorkResult),ComponentResults);
                        }
                        else throw new StudentResultAlreadyExistsException();
                    } else throw new CourseNotFoundException();
                }
                catch (CourseNotFoundException e){
                    System.out.println(e.getMessage());
                }
                catch (StudentResultAlreadyExistsException e){
                    System.out.println(e.getMessage());
                }
                catch (StudentNotRegisteredForTheCourse e){
                    System.out.println(e.getMessage());
                }
                catch (IOException e){
                    System.out.println(e.getMessage());
                }
            break;


            case 9:
                break;
            case 10://previous case 10
                break;




            }
        }
    }
}
