package com.group1;

import java.util.ArrayList;

public class Validation
{
    //checking if exist, checking if input is legit, all checking method....

    public static boolean studentExists(String matricNumber, DataContainer container){
        if(container.getStudentsList().isEmpty())
            return false;
        for(Student student:container.getStudentsList()){
            if(matricNumber.toUpperCase().equals(student.getMatricNumber()))
                return true;
        }
        return false;
    }
    public static boolean checkCourseExisted(String courseCode, DataContainer container){

        if(container.getCourseList().isEmpty())
            return false;
        for ( Course course:container.getCourseList()){
            if (course.getCourseTitle().equals(courseCode.toUpperCase()))
                return true;
        }
        return false;
    }



    public static int checkStudentResultsRecord(Student student, String coursetitle)
    {
        for(String key:student.getCourseAndResult().keySet())
        {
            if (key.equals(coursetitle))
            {
                ArrayList<AssessmentComponent> listtemp = student.getCourseAndResult().get(key);
                return listtemp.size();
            }
        }

        return 0;

    }

    public static Boolean checkWhetherStudentRegisteredForACourse(Student student, String coursetitle)
    {
        for(String key:student.getCourseAndResult().keySet())
        {
            if (key.equals(coursetitle))
                return true;
        }
        return false;
    }

    public static Boolean checkWhetherHasAssessmentWeightage(Course course)
    {
        if (course.getComponents().isEmpty())
            return false;
        else
            return true;
    }




    public static boolean validateNumberInput(String str){
        try{
            int x = Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }

    }


    public static boolean validateFloatInput(String str){
        try{
            float x = Float.parseFloat(str);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
    public static boolean validateWeightageSum(float f)
    {
        if (f == 1)
            return true;
        else
            return false;
    }

    public static boolean validateNameInput(String str){
        if (str.matches("[a-zA-Z\\s]*")) {
            return true;
        }
        else return false;
    }

    public static boolean validateGroupNameInput(String str){
        if (str.matches("[a-zA-Z0-9]*")) {
            return true;
        }
        else return false;
    }

}
