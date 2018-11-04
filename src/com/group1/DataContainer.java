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

    public void AddStudent(Student student){
        studentsList.add(student);
    }

    public void AddCourse(Course course){
        courseList.add(course);
    }
    public boolean studentExists(String matricNumber){
        for(Student student:studentsList){
            if(matricNumber.equals(student.getMatricNumber())
            return true;
        }
        return false;
    }
    public boolean CheckCourseExisted(String courseCode){
        for ( Course course: courseList){
            if (course.GetCourseTitle().equals(courseCode))
                return true;
        }
        return false;
    }


    public static Course AddCourse() {
        System.out.println("Add Course: Please input the course title of the course. The course title shall only contain alphabets and numbers, not spaces.");
        Scanner in = new Scanner(System.in);
        String courseTitle = "Default";
        boolean titleValid = false;
        while (!titleValid) {
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

    public static Student AddStudent (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Add Student: Please input the Student Name.");
        String studentName = "Default";
        String matric = "Default";
        studentName = sc.nextLine();
        try {
            if (!InputManager. ValidateNameInput(studentName))
                throw new NameNotValidException();
            if(FileReadManager.CheckStudentExistsByName(studentName)){
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
        return new Student(studentName,matric);
    }




}
