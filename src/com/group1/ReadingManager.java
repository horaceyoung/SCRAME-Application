package com.group1;

<<<<<<< HEAD
import javax.xml.crypto.Data;
import java.lang.reflect.Array;
import java.util.ArrayList;
=======
import java.io.File;
import java.util.Scanner;
>>>>>>> 4b2a9b7055b7f22dbd8d6c2ca96489f41949092a

public class ReadingManager
{

<<<<<<< HEAD
    public ArrayList<Tutorial> GetCourseTutorials(String courseTitle, DataContainer dataContainer){
        ArrayList<Tutorial> tutorialList = new ArrayList<Tutorial>();

        return tutorialList;
    }
=======
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
    // reading methods all goes in to this (printing.....), combine unnecessary method
>>>>>>> 4b2a9b7055b7f22dbd8d6c2ca96489f41949092a
}

