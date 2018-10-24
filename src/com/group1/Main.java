package com.group1;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner in = new Scanner(System.in);
        String intro = "Welcome to the SCRAME application console: \nPress the corresponding number to use: \n"
                + "1: Add a new student:\n"
		+ "2: Add a new course:\n"
		+ "3: Register student for a course:\n"
		+ "4: Check available slot in a class:\n"
		+ "5: Print student list by lecture, tutorial or laboratory:\n"
                + "6. Enter course assessment components weightage and sub-component weightage.\n"
                + "7: Exit:";

        int choice = 0;
        while(choice!=7){
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
                        if(FileReadManager.CheckDuplicateCourses(newCourse1.GetCourseTitle())){
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
			            break;
			    
			    
		        case 4:
			// Testcase 4: Check available slot in a class
			        break;
			    
			    
		case 5:
		    //Testcase 5: Print student list by lecture, tutorial or lab
            System.out.println("Please key in Course Code");
            Scanner sc = new Scanner(System.in);
            String courseName = sc.nextLine();
            try{
                if (!FileReadManager.CheckDuplicateCourses(title6))
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
                        if (!FileReadManager.CheckDuplicateCourses(title6))
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
			break;




            }
        }

    }
}
