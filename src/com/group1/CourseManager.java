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






    public static void printStudentListByLecture(String courseName) throws IOException {
        File tutFile = new File("data/Tutorials.txt");
        Scanner tutScanner = new Scanner(tutFile);
        String studentName;
        while (tutScanner.hasNext()) {
            String[] tutorialGroup = tutScanner.nextLine().split("\t");
            if (tutorialGroup[0].toUpperCase().equals(courseName.toUpperCase())) {
                for (int i = 4; i < tutorialGroup.length; i++) {
                    studentName = Student.getNameFromMatric(tutorialGroup[i]);
                    System.out.println("   "+studentName + "  ");

                }
            } else {
                if (tutorialGroup.length<=4)
                    System.out.println("Currently There's no student registered in " + courseName);

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

                System.out.println(tutorialGroup[1] + "  Students List: ");

                for (int i = 4; i < tutorialGroup.length; i++) {

                    studentName = Student.getNameFromMatric(tutorialGroup[i]);
                    System.out.println("   "+studentName + "   ");

                }
            if (tutorialGroup.length<=4)
                System.out.println("    Currently There's no student registered in  " + tutorialGroup[1]);
            }
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

                for (int i = 4; i < labGroup.length; i++) {

                    studentName = Student.getNameFromMatric(labGroup[i]);
                    System.out.println("   "+studentName + "   ");

                }
                if (labGroup.length<=4)
                    System.out.println("    Currently there's no student registered in " + labGroup[1]);
            }
        }
    }





}
