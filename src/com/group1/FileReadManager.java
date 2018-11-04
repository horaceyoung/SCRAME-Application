package com.group1;

import Exceptions.CourseNameNotValidException;
import Exceptions.TutorialLabNotAvailableException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReadManager {
    private static ArrayList<AssessmentComponent> components = new ArrayList<>();


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





    public static ArrayList<AssessmentComponent> GetCourseWorkComponentsList(String courseTitle) throws IOException{
        File courseFile = new File("data/Component.txt");
        Scanner courseScanner = new Scanner(courseFile);
        while(courseScanner.hasNext()){
            String[] currentCourse = courseScanner.nextLine().split("\t");
            if(currentCourse[0].equals(courseTitle)){
                int i =3;
/*                if (currentCourse.length < 3){
                    throw new CourseNoCourseWorkException();
                }*/

                while(i<currentCourse.length){
                    String[] eachComponent = currentCourse[i].split(":");
                    AssessmentComponent component = new AssessmentComponent(Float.parseFloat(eachComponent[1]),eachComponent[0]);
                    components.add(component);
                    i++;
                }
            }
        }

        return components;
    }



}
