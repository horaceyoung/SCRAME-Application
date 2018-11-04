package com.group1;

import java.util.Scanner;

public class EditingManager
{
    // editing methods all goes in to this (adding, updating), combine unnecessary method
    public static Course AddCourseComponent(String courtsetitle, Course newcourse6) {


        String[] component = {"Exam", "Coursework"};

        while (true) {
            for (String compname : component) {
                newcourse6.AssignComponentWeightage(newcourse6.GetComponents(), compname);
            }
            float weightagesum = 0;
            for (AssessmentComponent componentnew : newcourse6.GetComponents()) {
                weightagesum += componentnew.getWeightage();
            }
            System.out.println(weightagesum);
            if (Validation.ValidateWeightageSum(weightagesum)) {
                break;
            } else {
                System.out.println("The total weightage you have entered is not valid. It should sum up to 1\n");
                newcourse6.ClearComponentWeightage(newcourse6.GetComponents());

            }
        }


        while (true) {
            String temp;
            while (true) {
                System.out.println("Do we have sub-component for coursework? Yes: Enter 1  No: Enter 0");
                Scanner ynscanner = new Scanner(System.in);
                temp = ynscanner.nextLine();
                if (Validation.ValidateNumberInput(temp)) {
                    break;

                } else {
                    System.out.println("Input not valid, please enter 0 or 1\n");
                }


            }

            if (Integer.parseInt(temp) == 0) {
                break;
            }

            while (true) {
                System.out.println("How many sub-components do u have?");
                Scanner numscanner = new Scanner(System.in);
                temp = numscanner.nextLine();
                if (InputManager.ValidateNumberInput(temp) && Integer.parseInt(temp) > 1) {
                    break;

                } else {
                    System.out.println("Input not valid, please enter number greater than 1.\n");
                }
            }
            int subcomnum = Integer.parseInt(temp);
            String[] subcoponent = new String[subcomnum];

            for (int x = 1; x <= subcomnum; x++) {
                System.out.println("Please enter sub-component " + x + " name");
                Scanner subcomponentscanner = new Scanner(System.in);
                subcoponent[x - 1] = subcomponentscanner.nextLine();


            }
            while (true) {
                for (String subcomname : subcoponent) {
                    newcourse6.AssignComponentWeightage(newcourse6.GetSubComponents(), subcomname);
                }
                float subweightagesum = 0;
                for (AssessmentComponent componentnew : newcourse6.GetSubComponents()) {
                    subweightagesum += componentnew.getWeightage();
                }
                if (InputManager.ValidateWeightageSum(subweightagesum)) {
                    break;
                } else {
                    System.out.println(subweightagesum);
                    System.out.println("The total weightage you have entered is not valid. It should sum up to 1\n");
                    newcourse6.ClearComponentWeightage(newcourse6.GetSubComponents());
                }
            }
            break;


        }

        return newcourse6;

    }
}
