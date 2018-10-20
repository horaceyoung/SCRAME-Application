package com.group1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        in.nextLine();
        switch (choice){
            case 0:
                String courseTitle = in.nextLine();
                Course newCourse = CourseManager.AddCourse("CZ1003");
                newCourse.AddTutorialGroups();
                break;
        }
    }
}
