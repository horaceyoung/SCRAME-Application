package com.group1;

import java.io.File;
import java.util.Scanner;

public class ReadingManager
{

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
}

