package com.group1;

import java.util.ArrayList;

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

    public boolean CheckCourseExisted(String courseCode){
        for ( Course course: courseList){
            if (course.GetCourseTitle().equals(courseCode))
                return true;
        }
        return false;
    }


}
