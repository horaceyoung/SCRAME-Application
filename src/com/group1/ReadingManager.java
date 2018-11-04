package com.group1;

import javax.xml.crypto.Data;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
}
