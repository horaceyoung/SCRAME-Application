package com.group1;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadingManager
{

    public ArrayList<Tutorial> GetCourseTutorials(String courseTitle, DataContainer dataContainer){
        ArrayList<Tutorial> tutorialList = new ArrayList<Tutorial>();

        return tutorialList;
    }

    public static String getNameFromMatric (String marticNumber,DataContainer dataContainer) {
        try{
            for(Student currStudent:dataContainer.getStudentsList()) {
                if(currStudent.getMatricNumber().equals(marticNumber))
                    return currStudent.GetStudentName();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "Default";
    }


    public static void printStudentListByTutorial(String courseName, String tutGroupName, DataContainer dataContainer) throws IOException {
        for(Course currCourse:dataContainer.getCourseList())
        {
            if(currCourse.GetCourseTitle().equals(courseName.toUpperCase()))
            {
                for (Tutorial currTut: currCourse.GetTutorialList()){
                    System.out.println("Students registered in " + tutGroupName +": ")
                    {
                        for ( currTut: currCourse.GetTutorialList()){
                    }
                }
                return;
            }
        }


    }
}
