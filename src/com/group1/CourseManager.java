package com.group1;

import Exceptions.CourseNameNotValidException;
import Exceptions.WeightageNotValidException;

import java.io.IOException;
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
        for (int a = 0; a <= 1; a++)
        {

            while (true)
            {
                try
                {
                    Scanner s = new Scanner(System.in);

                    System.out.println("Please enter the "+component[a]+" weightage:\n");

                    weightage[a] = s.nextInt();
                    if (weightage[a] <= 0 || weightage[a] >= 1)
                    {
                        throw new WeightageNotValidException();
                    }
                    else
                    {
                        break;

                    }
                }

                catch (WeightageNotValidException e)
                {
                    System.out.println(e.getMessage());
                }
            }

        }




    }


}
