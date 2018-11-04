package com.group1;

import Exceptions.CourseNameNotValidException;
import Exceptions.NameNotValidException;

import java.util.ArrayList;
import java.util.Scanner;

public class DataContainer implements java.io.Serializable{
    public DataContainer(){

    }

    private ArrayList<Student> studentsList = new ArrayList<>();
    private ArrayList<Course> courseList = new ArrayList<>();
    public ArrayList<Professor> professors = new ArrayList<>();

    public ArrayList<Course> getCourseList()
    {
        return courseList;
    }

    public ArrayList<Student> getStudentsList()
    {
        return studentsList;
    }

    public ArrayList<Professor> getProfessors(){return professors;}



    public void AddCourse() {
        System.out.println("Add Course: Please input the course title of the course. The course title shall only contain alphabets and numbers, not spaces.");
        Scanner in = new Scanner(System.in);
        String courseTitle = "Default";
        boolean titleValid = false;
        Course newCourse;
        while (!titleValid) {
            courseTitle = in.nextLine();
            try {
                if (!Validation.ValidateGroupNameInput(courseTitle)) {
                    throw new CourseNameNotValidException();
                }
                titleValid = true;
                System.out.println("Create Course Success: The course with course title " + courseTitle + " has been successfully created");

            } catch (CourseNameNotValidException e) {
                System.out.println(e.getMessage());
            }
        }

        try{
            if(Validation.CheckCourseExisted(courseTitle, this)){
                System.out.println("Add Course Failed: a course with the same course title has already been added");
            }
            else{
                newCourse = new Course(courseTitle.toUpperCase());
                newCourse.AssignCoordinator();
                // Add Labs and Tutorialss
                newCourse.AddTutorialLabGroups("Tutorial");
                newCourse.AddTutorialLabGroups("Lab");
                courseList.add(newCourse);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void AddStudent (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Add Student: Please input the Student Name.");
        String studentName = "Default";
        String matric = "Default";
        studentName = sc.nextLine();
        try {
            if (!Validation. ValidateNameInput(studentName))
                throw new NameNotValidException();
            if(Validation.studentExists(matric,this)){
                System.out.println("Add Student Failed: Student has already been added.");
            }
            else{
                System.out.println("Please input the Student's Matric Number.");
                matric = sc.nextLine();
                System.out.println("Add student Success: student " + studentName + ": "+  matric+ " has been successfully added");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());


        }
        studentsList.add(new Student(studentName.toUpperCase(), matric.toUpperCase()));
    }




}
