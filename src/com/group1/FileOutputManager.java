package com.group1;

import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.Iterator;

public class FileOutputManager
{


    //This output manage includes all method used to write or append data to respective data file.txt.
    //The format for the txt file is that for each line, different field is separated by tab and different sub-field is separated by space


    public static void WriteStudent(String name, String matric)
    {
        //Students.txt
        //filed 0: name
        //field 1: matric number

        try
        {
            File file = new File("data/Students.txt");
            file.createNewFile();   //create file if not exist
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            String line = name+"\t"+matric+"\n";
            writer.write(line);
            writer.close();



        }
        catch (IOException e2)
        {
            System.out.println(e2.getMessage());

        }


    }

    public static void WriteResults(String matric, String coursecode, String[] resultsarray) //resultsarray[0]: exam;
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            String line = matric+"\t"+coursecode+"\t"+resultsarray[0]+"\t"+resultsarray[1]+"\t";

            writer.write(line);

            while (true)
            {
                int count = 2;
                if(resultsarray[count] != null)
                {
                    writer.write(resultsarray[count]+" ");
                }
                else
                {
                    writer.write("\n");
                    break;
                }
                count++;
            }
            writer.newLine();
            writer.close();



        }
        catch (IOException e2)
        {
            System.out.println(e2.getMessage());

        }
    }


    public static void WriteCourse(Course course)
    {
        //Courses.text
        //filed 0: course title
        //field 1: coordinater
        //field 2: coordinator school
        try
        {
            File file = new File("data/Courses.txt");
            file.createNewFile();   //create file if not exist
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            String line = course.GetCourseTitle()+"\t"+course.GetCoordinator().getCoordinatorName()+"\t"+course.GetCoordinator().getCoordinatorSchool();
            writer.write(line);
            writer.newLine();
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void WriteSessions(Course course){

        //Tutorial.text Lab.txt
        //filed 0: course title
        //field 1: groupname
        //field 2: total vacancy
        //field 3: current vacancy

        try
        {
            //Write all the tutorials
            File tutFile = new File("data/Tutorials.txt");
            BufferedWriter tutWriter = new BufferedWriter(new FileWriter(tutFile, true));
            ArrayList<Tutorial> tutorialList = course.GetTutorialList();
            for(Tutorial tutorialGroup : tutorialList){
                String newTutGroup = course.GetCourseTitle() + "\t" + tutorialGroup.GetName() + "\t" + String.valueOf(tutorialGroup.GetTotalVacancy())+ "\t" + String.valueOf(tutorialGroup.GetTotalVacancy());
                tutWriter.write(newTutGroup);
                tutWriter.newLine();
            }
            tutWriter.close();

            //Write all the labs
            File labFile = new File("data/Labs.txt");
            BufferedWriter labWriter = new BufferedWriter(new FileWriter(labFile, true));
            ArrayList<Lab> labList = course.GetLabList();
            for(Lab labGroup : labList){
                String newLabGroup = course.GetCourseTitle() + "\t" + labGroup.GetName() + "\t" + String.valueOf(labGroup.GetTotalVacancy())+ "\t" + String.valueOf(labGroup.GetTotalVacancy());
                labWriter.write(newLabGroup);
                labWriter.newLine();
            }
            labWriter.close();


        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void WriteComponents(Course course)
    {
        //ResultComponent.txt
        //field 0: course name
        //field 1: Exam: 'Weightage'
        //field 2: Coursework: 'weightage'
        //field 3...: 'coursework sub-component name': 'Weightage'

        try
        {
            File file = new File("data/Component.txt");
            file.createNewFile();   //create file if not exist
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(course.GetCourseTitle()+"\t");
            for (AssessmentComponent maincomp: course.GetComponents())
            {

                writer.write(maincomp.getWeightage() maincomp.getWeightage()+"\t");
            }
            for (AssessmentComponent subcomp: course.GetSubComponents())
            {
                writer.write(subcomp.getWeightage()+" ");
            }


            writer.write(line);
            writer.newLine();
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }



}
