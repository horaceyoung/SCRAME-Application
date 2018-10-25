package com.group1;

import Exceptions.NameNotValidException;

import java.io.IOException;
import java.util.Scanner;

public class StudentManager {
    public static Student AddStudent (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Add Student: Please input the Student Name.");        
        String studentName = "Default";
        String matric = "Default";
        boolean nameValid = false;
        while(!nameValid) {
            studentName = sc.nextLine();
            try {
                if (!InputManager. ValidateNameInput(studentName)) 
                      throw new NameNotValidException ();
                System.out.println("Please input the Student's Matric Number.");
                matric = sc.nextLine();
                if(FileReadManager.CheckStudentExists(matric)){
                            System.out.println("Add Student Failed: Student has been added.");
                        }
                else{
                nameValid = true;
                System.out.println("Add student Success: student " + studentName + ": "+  matric+ " has been successfully added");
                }
            } 
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        return new Student(studentName,matric);
    }

}
