package com.group1;

import Exceptions.*;

import javax.imageio.stream.FileImageOutputStream;
import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.group1.ReadingManager.CheckStudentRegisteredForCourse;

public class Main {

    public static void main(String[] args) {
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
        try{
            FileInputStream fileIn = new FileInputStream("data/SerializableDataContainer.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            dataContainer = (DataContainer) objectIn.readObject();
            dataContainer.professors.add(new Professor("Li Fang", "SCSE"));
            dataContainer.professors.add(new Professor("Jack", "SCSE"));
            dataContainer.professors.add(new Professor("John", "SCSE"));
            dataContainer.professors.add(new Professor("Peter", "SCSE"));

        }
        catch (Exception e){
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
                        + "| 7: Enter course marks for students                |" + "\n"
                        + "| 8. Print course statistics                        |" + "\n"
                        + "| 9. Print student transcript                       |" + "\n"
                        + "|===================================================|" + "\n"
                        + "| 0. Save and quit                                  |" + "\n"
                        + "|===================================================|" + "\n";


        int choice = 0;
        while(choice!=11){
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

                    System.out.println("Register course: Please input the matric number of the student to register: ");
                    EditingManager EM = new EditingManager();
                    studentMatric = in.nextLine();
                    try{
                        if(!Validation.studentExists(studentMatric,dataContainer))
                            throw new StudentNotExistException();
                        else{
                            ArrayList<Student> studentList = dataContainer.getStudentsList();
                            for(Student student:studentList){
                                if(studentMatric.equals(student.getMatricNumber()))
                                    newStudent = student;
                            }

                        }
                        System.out.println("Register course: Please input the course title you want to register with: ");
                        courseTitle = in.nextLine();

                        if(!Validation.CheckCourseExisted(courseTitle,dataContainer)){
                            throw new CourseNotFoundException();
                        }
                        if(!ReadingManager.CourseHaveVacancy(courseTitle,dataContainer)){
                            throw new CourseNoVacancyException();}
                        if(ReadingManager.CheckStudentRegisteredForCourse(newStudent,newCourse)){
                            throw new StudentAlreadyRegisteredForThisCourseException(newStudent,newCourse);
                        }

                        ArrayList<Course> courseList = dataContainer.getCourseList();
                        for (Course course : courseList) {
                            if (courseTitle.equals(course.GetCourseTitle())) {
                                newCourse = course;
                                break;

                            }
                        }
                        EM.RegisterStudentToCourseLecture(newStudent,courseTitle,dataContainer);

                        if(newCourse.HaveTutorial()==false)
                            break;
                        System.out.println("Please select a tutorial to be enrolled in:");
                        int i=1;
                        while(i<=newCourse.GetTutorialList().size()){
                            System.out.println(i+". "+newCourse.GetTutorialList().get(i-1).sessionName+"\t");
                            i++;
                        }
                        tutorialName = in.nextLine();

                        if(!EM.RegisterStudentToTutorial(newStudent,courseTitle,tutorialName,dataContainer))
                            break;

                        if(newCourse.HaveLab()==false)
                            break;

                        System.out.println("Please select a lab to be enrolled in:");
                        i=1;
                        while(i<=newCourse.GetLabList().size()){
                            System.out.println(i+". "+newCourse.GetLabList().get(i-1).sessionName+"\t");
                            i++;
                        }
                        labName = in.nextLine();
                        EM.RegisterStudentToLab(newStudent,courseTitle,labName,dataContainer);
                    }
                    catch (CourseNoVacancyException e){System.out.println(e.getMessage());}
                    catch (StudentAlreadyRegisteredForThisCourseException e){System.out.print( e.getMessage());}
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                    break;


            case 4:
            // Testcase 4: Check available slot in a class
                System.out.println("Check Session Vacancy: Please input the course title you want to check: ");
                courseTitle = in.nextLine();
                try{
                    if(Validation.CheckCourseExisted(courseTitle, dataContainer)){
                        int sessionChoice;
                        System.out.println("Check Session Vacancy: Please select the type of session you wish to check by inputting corresponding integer value: \n 1. Tutorial \n2.Lab \n");
                        sessionChoice=in.nextInt();
                        switch (sessionChoice){
                            case 1:
                                ReadingManager.PrintTutorialVacancy(courseTitle, dataContainer);
                                break;
                            case 2:
                                ReadingManager.PrintLabVacancy(courseTitle, dataContainer);
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
                            if (!Validation.CheckCourseExisted(courseName.toUpperCase(),dataContainer))
                                System.out.println("The course you entered does not exist. Please add this course first.\n");

                            else{
                    System.out.println("Key in 'Lec' to print by lecture || 'Tut' to print by tutorial || 'Lab' to print by lab");
                                String printList = sc.nextLine().toUpperCase();

                                if(printList.equals("LEC"))
                                    ReadingManager.printStudentListByLecture(courseName,dataContainer);

                                else if(printList.equals("TUT")) {
                                    String tutGroupName = sc.nextLine();
                                    if(!Validation.CheckTutExisted(courseName,tutGroupName,dataContainer))
                                        System.out.println("There is no " + tutGroupName + " in "+courseName);
                                    ReadingManager.printStudentListByTutorial(courseName,tutGroupName,dataContainer);
                                }
                                else if(printList.equals("LAB")) {
                                    String labGroupName = sc.nextLine();
                                    if(!Validation.CheckLabExisted(courseName,labGroupName,dataContainer))
                                        System.out.println("There is no " + labGroupName + " in "+courseName);
                                    ReadingManager.printStudentListByLab(courseName, labGroupName, dataContainer);
                                }
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
            // Testcase 7: Enter coursework mark

                System.out.println("Please enter the course title:");
                Scanner scanner = new Scanner(System.in);
                String title7 = scanner.next();


                try
                {
                    if (Validation.CheckCourseExisted(title7, dataContainer))
                    {
                        System.out.println("Please enter the student's matriculation number:");
                        String matric = scanner.next();
                        if(Validation.studentExists(matric, dataContainer))
                        {

                            EditingManager.AssignComponentResults(matric, title7, dataContainer);
                        }
                        else
                        {
                            throw new StudentNotExistException();
                        }
                    }
                    else
                    {

                        throw new CourseNotFoundException();
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                    scanner.nextLine();
                }

                break;




         case 9:
        	 //testcase 9: Print course statistics
        	 //Show grade percentage for overall (exam + coursework)
        	 //exam only and coursework only.
            	System.out.println("Please enter Course Code to check for course statistics");
                Scanner sc1 = new Scanner(System.in);
                String coursetitle = sc1.nextLine();
                    if (!Validation.CheckCourseExisted(coursetitle, dataContainer))
                        System.out.println("The course you entered does not exist. Please enter another course code.\n");
                    else{
                    	EditingManager.printCourseStatistics(coursetitle, dataContainer);
                     }
                    break;

            case 10:
            	//Print student transcript.
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
            			EditingManager.printTranscript(studentMatricTranscript, dataContainer);
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
