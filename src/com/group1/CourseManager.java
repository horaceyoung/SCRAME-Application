package com.group1;

import Exceptions.CourseNameNotValidException;
import Exceptions.WeightageNotValidException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseManager {

    public static Course AddCourse(){
        System.out.println("Add Course: Please input the course title of the course. The course title shall only contain alphabets and numbers, not spaces.");
        Scanner in = new Scanner(System.in);
        String courseTitle = "Default";
        boolean titleValid = false;
        while(!titleValid) {
            courseTitle = in.nextLine();
            try {
                if (!InputManager.ValidateGroupNameInput(courseTitle)) {
                    throw new CourseNameNotValidException();
                }
                titleValid = true;
                System.out.println("Create Course Success: The course with course title " + courseTitle + " has been successfully created");

            } catch (CourseNameNotValidException e) {
                System.out.println(e.getMessage());
            }
        }
        return new Course(courseTitle);
    }

    public static void AddCourseComponent(String courtsetitle)
    {

        int[] weightage = new int[2];
        String[] component = {"Exam", "Coursework"};
        Course newcourse6 = new Course(courtsetitle);
        while (true)
        {
            for (int a = 0; a <= 1; a++)
            {
                newcourse6.AssignComponentWeightage(newcourse6.GetComponents(), component[a]);
            }
            float weightagesum = 0;
            for(AssessmentComponent componentnew : newcourse6.GetComponents())
            {
                 weightagesum += componentnew.getWeightage();
            }
            if (InputManager.ValidateWeightageSum(weightagesum))
            {
                break;
            }
        }


        while (true)
        {
            String yn;
            while (true)
            {
                System.out.println("Do we have subcomponent? Yes: Enter 1  No: Enter 0\n");
                Scanner ynscanner = new Scanner(System.in);
                yn = ynscanner.nextLine();
                if (InputManager.ValidateNumberInput(yn))
                {
                    break;

                }
                else
                {
                    System.out.println("Input not valid, please enter 0 or 1\n");
                }


            }

            if(Integer.parseInt(yn) == 0)
            {
                break;
            }

            while (true)
            {
                System.out.println("How many sub-components do u have?\n");
                Scanner ynscanner = new Scanner(System.in);
                yn = ynscanner.nextLine();
                if (InputManager.ValidateNumberInput(yn) || Integer.parseInt(yn) > 1)
                {
                    break;

                }
                else
                {
                    System.out.println("Input not valid, please enter number greater than 1.\n");
                }
            }
            int subcomnum = Integer.parseInt(yn);

            for (int x = 1; x <= subcomnum; x++)
            {
                System.out.println("Please enter Sub-component "+ x +" name.\n");
                Scanner subcomponentscanner = new Scanner(System.in);
                String subcom = subcomponentscanner.nextLine();

            }
        }






    }


}
