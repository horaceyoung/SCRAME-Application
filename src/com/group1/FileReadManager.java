package com.group1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReadManager {
    public static boolean CheckDuplicateCourses(String courseTitle) throws IOException {
        File courseFile = new File("data/Courses.txt");
        Scanner coursesScanner = new Scanner(courseFile);
        while(coursesScanner.hasNext()){
            String[] currentCourse = coursesScanner.nextLine().split("\t");
            if (currentCourse[0].equals(courseTitle)){
                return true;
            }
        }
        return false;
    }

    public static boolean CheckDuplicateStudent(String studentName) throws IOException {
        File studentFile = new File("data/Students.txt");
        Scanner studentSC= new Scanner(studentFile);
        while(studentSC.hasNext()){
            String[] currStudent= studentSC.nextLine().split("\t");
            if (currStudent[0].equals(studentName))
                return true;     
        }
        return false;
}



}
