package com.group1;

<<<<<<< HEAD
import Exceptions.CourseNoLabException;
import Exceptions.CourseNoTutorialException;
import Exceptions.LabGroupNonExistentException;
import Exceptions.TutorialGroupNonExistentException;
=======
import Exceptions.CourseNameNotValidException;
import Exceptions.TutorialLabNotAvailableException;
>>>>>>> 177c9e154489c1eee522530cc83494890d133b96

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReadManager {
    public static boolean CheckCourseExists(String courseTitle) throws IOException{
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

    public static boolean CheckStudentExists(String matric) throws IOException {
        File studentFile = new File("data/Students.txt");
        Scanner studentSC= new Scanner(studentFile);
        while(studentSC.hasNext()){
            String[] currStudent= studentSC.nextLine().split("\t");
            if (currStudent[1].equals(matric))
                return true;     
        }
        return false;
    }

<<<<<<< HEAD
    public static boolean HaveTut(String courseTitle){
        File tutorialFile = new File("data/Tutorials.txt");
        Scanner tutoialScanner = new Scanner(tutorialFile);
        while(tutoialScanner.hasNext()){
            String[] currentCourse = tutoialScanner.nextLine().split("\t");
            if (currentCourse[0].equals(courseTitle)){
                return true;
            }
        }
        return false;
    }
    public static boolean HaveLab(String courseTitle){
        File labFile = new File("data/Labs.txt");
        Scanner labScanner = new Scanner(labFile);
        while(labScanner.hasNext()){
            String[] currentCourse = labScanner.nextLine().split("\t");
            if (currentCourse[0].equals(courseTitle)){
                return true;
            }
        }
        return false;
    }

    public static String GetTutMaxVacancy(String courseTitle, String TutGrpName) {
=======
    public static String GetStudentInfo(String matric) throws IOException{
        File studentFile = new File("data/Students.txt");
        Scanner studentSC= new Scanner(studentFile);
        while(studentSC.hasNext()){
            String[] currStudent= studentSC.nextLine().split("\t");
            if (currStudent[1].equals(matric))
                return currStudent[0];
        }
        return "default";
    }

    public static void GetCourseSessions(String courseTitle, Course course) throws IOException, TutorialLabNotAvailableException{
        File courseFile = new File("data/Tutorials.txt");
        Scanner coursesScanner = new Scanner(courseFile);
        boolean available = false;
        System.out.println("The Tutorials and Labs of " + courseTitle + " is as following: ");
        while(coursesScanner.hasNext()){
            String[] currentCourse = coursesScanner.nextLine().split("\t");
            if (currentCourse[0].equals(courseTitle)&&Integer.valueOf(currentCourse[3])>0){
                    available = true;
                    course.AddTutorial(new Tutorial(currentCourse[1]));
                    System.out.println("Tutorials: " + currentCourse[1]+ " total slots: " + currentCourse[2] + " / available slots: " + currentCourse[3] + "\n");

            }
        }

        courseFile = new File("data/Labs.txt");
        coursesScanner = new Scanner(courseFile);
        while(coursesScanner.hasNext()){
            String[] currentCourse = coursesScanner.nextLine().split("\t");
            if (currentCourse[0].equals(courseTitle)&& Integer.valueOf(currentCourse[3])>0){
                available = true;
                course.AddLab(new Lab(currentCourse[1]));
                System.out.println("Labs: " + currentCourse[1]+ " total slots: " + currentCourse[2] + " / available slots: " + currentCourse[3] + "\n");
            }
        }

        if(!available)
            throw new TutorialLabNotAvailableException();

    }

>>>>>>> 177c9e154489c1eee522530cc83494890d133b96

        String vacancy = "Error";
        try {
            if (!HaveTut(courseTitle))
                throw new CourseNoTutorialException();
            File tutorialFile = new File("data/Tutorials.txt");
            Scanner tutoialScanner = new Scanner(tutorialFile);
            while (tutoialScanner.hasNext()) {
                String[] currentCourse = tutoialScanner.nextLine().split("\t");
                if (currentCourse[0].equals(courseTitle)) {
                    if (currentCourse[1].equals(TutGrpName)) {
                        vacancy = currentCourse[4];
                        break;
                    }
                    break;
                }

            }
            if(vacancy.equals("Error")) throw new TutorialGroupNonExistentException();

        } catch (CourseNoTutorialException e) {
            System.out.println(e.getMessage());
        } catch (TutorialGroupNonExistentException e) {
            System.out.println(e.getMessage());
        }
        return vacancy;
    }
    public static String GetTutCurrentVacancy(String courseTitle, String TutGrpName) {

        String vacancy = "Error";
        try {
            if (!HaveTut(courseTitle))
                throw new CourseNoTutorialException();
            File tutorialFile = new File("data/Tutorials.txt");
            Scanner tutoialScanner = new Scanner(tutorialFile);
            while (tutoialScanner.hasNext()) {
                String[] currentCourse = tutoialScanner.nextLine().split("\t");
                if (currentCourse[0].equals(courseTitle)) {
                    if (currentCourse[1].equals(TutGrpName)) {
                        vacancy = currentCourse[3];
                        break;
                    }
                    break;
                }

            }
            if(vacancy.equals("Error")) throw new TutorialGroupNonExistentException();

        } catch (CourseNoTutorialException e) {
            System.out.println(e.getMessage());
        } catch (TutorialGroupNonExistentException e) {
            System.out.println(e.getMessage());
        }
        return vacancy;
    }
    public static String GetLabMaxVacancy(String courseTitle, String LabGrpName) {

        String vacancy = "Error";
        try {
            if (!HaveTut(courseTitle))
                throw new CourseNoLabException();
            File labFile = new File("data/Labs.txt");
            Scanner labScanner = new Scanner(labFile);
            while (labScanner.hasNext()) {
                String[] currentCourse = labScanner.nextLine().split("\t");
                if (currentCourse[0].equals(courseTitle)) {
                    if (currentCourse[1].equals(LabGrpName)) {
                        vacancy = currentCourse[4];
                        break;
                    }
                    break;
                }

            }
            if(vacancy.equals("Error")) throw new LabGroupNonExistentException();

        } catch (CourseNoLabException e) {
            System.out.println(e.getMessage());
        } catch (LabGroupNonExistentException e) {
            System.out.println(e.getMessage());
        }
        return vacancy;
    }
    public static String GetLabCurrentVacancy(String courseTitle, String LabGrpName) {

        String vacancy = "Error";
        try {
            if (!HaveTut(courseTitle))
                throw new CourseNoLabException();
            File labFile = new File("data/Labs.txt");
            Scanner labScanner = new Scanner(labFile);
            while (labScanner.hasNext()) {
                String[] currentCourse = labScanner.nextLine().split("\t");
                if (currentCourse[0].equals(courseTitle)) {
                    if (currentCourse[1].equals(LabGrpName)) {
                        vacancy = currentCourse[3];
                        break;
                    }
                    break;
                }

            }
            if(vacancy.equals("Error")) throw new LabGroupNonExistentException();

        } catch (CourseNoLabException e) {
            System.out.println(e.getMessage());
        } catch (LabGroupNonExistentException e) {
            System.out.println(e.getMessage());
        }
        return vacancy;
    }
}
