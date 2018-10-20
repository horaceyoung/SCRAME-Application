package com.group1;

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
            choice = in.nextInt();
            in.nextLine();
            switch (choice){
                case 1:
                    // Create the course
                    Course newCourse = CourseManager.AddCourse();
                    // Assign the coordinator
                    newCourse.AssignCoordinator();
                    boolean writeResult = FileManager.writeCourse(newCourse);
                    // Add Labs and Tutorials
                    newCourse.AddTutorialLabGroups("Tutorial");
                    newCourse.AddTutorialLabGroups("Lab");
                    break;
            }
        }

    }
}
