package com.group1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Validation
{
    //checking if exist, checking if input is legit, all checking method....

    public static boolean studentExists(String matricNumber, DataContainer container){

        for(Student student:container.getStudentsList()){
            if(matricNumber.equals(student.getMatricNumber()))
                return true;
        }
        return false;
    }
    public static boolean CheckCourseExisted(String courseCode, DataContainer container){
        for ( Course course:container.getCourseList()){
            if (course.GetCourseTitle().equals(courseCode))
                return true;
        }
        return false;
    }
    public static boolean CheckTutExisted(String courseName, String tutGroupName, DataContainer container){
        for ( Course currCourse:container.getCourseList()){
            if (currCourse.GetCourseTitle().equals(courseName)) {
                for (Tutorial currTut: currCourse.GetTutorialList()){
                    if(currTut.GetName().equals(tutGroupName))
                        return true;
                }
            }
        }
        return false;
    }

    public static boolean CheckLabExisted(String courseName, String labGroupName, DataContainer container){
        for ( Course currCourse:container.getCourseList()){
            if (currCourse.GetCourseTitle().equals(courseName)) {
                for (Lab currLab: currCourse.GetLabList()){
                    if(currLab.GetName().equals(labGroupName))
                        return true;
                }
            }
        }
        return false;
    }


    public static Boolean CheckStudentResultsRecord(Student student, String coursetitle)
    {
        for(String key:student.GetCourseAndResult().keySet())
        {
            if (key == coursetitle)
            {
                ArrayList<AssessmentComponent> listtemp = student.GetCourseAndResult().get(key);
                if (listtemp.isEmpty())
                    return false;
            }
        }
        return true;
    }

    public static Boolean CheckWhetherStudentRegisteredForACourse(Student student, String coursetitle)
    {
        for(String key:student.GetCourseAndResult().keySet())
        {
            if (key == coursetitle)
                return true;
        }
        return false;
    }

    public static Boolean CheckWhetherHasAssessmentWeightage(Course course)
    {
        if (course.GetComponents().isEmpty())
            return false;
        else
            return true;
    }




    public static boolean ValidateNumberInput(String str){
        try{
            int x = Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }

    }


    public static boolean ValidateFloatInput(String str){
        try{
            float x = Float.parseFloat(str);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
    public static boolean ValidateWeightageSum(float f)
    {
        if (f == 1)
            return true;
        else
            return false;
    }

    public static boolean ValidateNameInput(String str){
        if (str.matches("[a-zA-Z\\s]*")) {
            return true;
        }
        else return false;
    }

    public static boolean ValidateGroupNameInput(String str){
        if (str.matches("[a-zA-Z0-9]*")) {
            return true;
        }
        else return false;
    }

}
