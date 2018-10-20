package com.group1;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner in = new Scanner(System.in);
        String intro = "Welcome to the SCRAME application console: \n Press the corresponding number to use: \n"
                + "1: Add a new Course:\n"
                + "7: Exit:";
        System.out.println(intro);
        int choice = 0;
        while(choice!=7){
            System.out.println(intro);
            choice = in.nextInt();
            in.nextLine();
            switch (choice){
                case 1:
                    // Create the course
                    Course newCourse = CourseManager.AddCourse();
                    // Assign the coordinator
                    try{
                        if(FileReadManager.CheckDuplicateCourses(newCourse.GetCourseTitle())){
                            System.out.println("Add Course Failed: a course with the same course title has already been added");
                        }
                        else{
                            newCourse.AssignCoordinator();
                            FileOutputManager.CreateCourse(newCourse);
                            // Add Labs and Tutorialss
                            newCourse.AddTutorialLabGroups("Tutorial");
                            newCourse.AddTutorialLabGroups("Lab");
                            FileOutputManager.CreateSessions(newCourse);
                        }
                    }
                    catch (IOException e){
                        System.out.println(e.getMessage());
                    }

                    break;

            }
        }

    }
}
