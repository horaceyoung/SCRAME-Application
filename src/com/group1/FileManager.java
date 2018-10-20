package com.group1;

import java.io.*;
import java.lang.*;

public class FileManager
{
    public static boolean writeStudent(String name, String matric)
    {
        //Students.txt
        //filed 0: name
        //field 1: matric number

        try
        {
            File file = new File("data/Students.txt");
            file.createNewFile();   //create file if not exist
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String line = name+"\t"+matric+"\n";
            writer.write(line);
            writer.close();
            return true;


        }
        catch (IOException e2)
        {
            System.out.println("IOexception");
            return false;
        }


    }

    public static boolean writeResults(String matric, String coursecode, String[] resultsarray) //resultsarray[0]: exam;
                                                                                                //            [1]; coursework;
                                                                                                //            [2...]: different component
    {

        //Results.txt
        //filed 0: matric number
        //field 1: course name
        //field 2: final exam results
        //field 3: coursework results
        //field 4?: coursework component results separated by space
        try
        {
            File file = new File("data/Results.txt");
            file.createNewFile();   //create file if not exist
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String line = matric+"\t"+coursecode+"\t"+resultsarray[0]+"\t"+resultsarray[1]+"\t";

            writer.write(line);

            while (true)
            {
                int x = 2;
                if(resultsarray[x] != null)
                {
                    writer.write(resultsarray[x]+" ");
                }
                else
                {
                    writer.write("\n");
                    break;
                }
                x++;
            }
            return true;


        }
        catch (IOException e2)
        {
            System.out.println("IOexception");
            return false;
        }
    }


    public static boolean writeCourse(Course course)
    {
        //Students.txt
        //filed 0: name
        //field 1: matric number
        try
        {
            File file = new File("data/Courses.txt");
            file.createNewFile();   //create file if not exist
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String line = course.GetCourseTitle()+"\t"+course.GetCoordinator().getCoordinatorName()+"\t"+course.GetCoordinator().getCoordinatorSchool()+"\n";
            writer.write(line);
            writer.close();
            return true;
        }
        catch (IOException e2)
        {
            System.out.println("IOexception");
            return false;
        }


    }



}
