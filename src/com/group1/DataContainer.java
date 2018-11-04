package com.group1;

import java.util.ArrayList;

public class DataContainer implements java.io.Serializable{
    public DataContainer(){

    }
<<<<<<< HEAD

    private ArrayList<Student> studentsList = new ArrayList<>();
    private ArrayList<Course> courseList = new ArrayList<>();

    public void AddStudent(Student student){
        studentsList.add(student);
    }

    public void AddCourse(Course course){
        courseList.add(course);
    }

    public boolean CheckCourseExisted(String courseCode){
        for ( Course course: courseList){
            if (course.GetCourseTitle().equals(courseCode))
=======
    public ArrayList<Student> studentsList = new ArrayList<>();
    public ArrayList<Course> courseList = new ArrayList<>();
    public boolean studentExists(String matricNumber){
        for(Student student:studentsList){
            if(matricNumber.equals(student.getMatricNumber()))
>>>>>>> 78fdf64b58474486d980edad8f3116e84b919952
                return true;
        }
        return false;
    }
<<<<<<< HEAD

=======
>>>>>>> 78fdf64b58474486d980edad8f3116e84b919952

}
