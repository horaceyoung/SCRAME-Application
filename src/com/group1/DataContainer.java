package com.group1;

import Exceptions.CourseNameNotValidException;
import Exceptions.NameNotValidException;

import java.util.ArrayList;
import java.util.Scanner;

public class DataContainer implements java.io.Serializable{
    public DataContainer(){

    }

    /**
     * An arraylist of the student cohort
     */

    private ArrayList<Student> studentsList = new ArrayList<>();

    /**
     * An arraylist of all courses
     */
    private ArrayList<Course> courseList = new ArrayList<>();

    /**
     * An arraylist of all professors
     */
    public ArrayList<Professor> professors = new ArrayList<>();

    /**
     * Gets all the course objects
     * @return an arraylist of courses
     */

    public ArrayList<Course> getCourseList()
    {
        return courseList;
    }

    /**
     * Get all the student objects
     * @return an arraylist of student
     */
    public ArrayList<Student> getStudentsList()
    {
        return studentsList;
    }

    /**
     * Get all the professors
     * @return an arraylist of professors
     */

    public ArrayList<Professor> getProfessors(){return professors;}


    /**
     * A method to create and add a new course object into the datacontainer
     */
    public void addCourse() {
        System.out.println("Add Course: Please input the course title of the course. The course title shall only contain alphabets and numbers, not spaces.");
        Scanner in = new Scanner(System.in);
        String courseTitle = "Default";
        boolean titleValid = false;
        Course newCourse;
        while (!titleValid) {
            courseTitle = in.nextLine();
            try {
                if (!Validation.validateGroupNameInput(courseTitle)) {
                    throw new CourseNameNotValidException();
                }
                titleValid = true;
                System.out.println("Create Course Success: The course with course title " + courseTitle + " has been successfully created");

            } catch (CourseNameNotValidException e) {
                System.out.println(e.getMessage());
            }
        }

        try{
            if(Validation.checkCourseExisted(courseTitle, this)){
                System.out.println("Add Course Failed: a course with the same course title has already been added");
            }
            else{
                newCourse = new Course(courseTitle.toUpperCase());
                newCourse.AssignCoordinator(this);
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

    /**
     * A method to create and add a new student object to the datacontainer
     */

    public void addStudent(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Add Student: Please input the Student Name.");
        String studentName = "Default";
        String matric = "Default";
        studentName = sc.nextLine();
        try {
            if (!Validation.validateNameInput(studentName))
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
