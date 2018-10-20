package com.group1;

public class CourseManager {
    public static Course AddCourse(String courseTitle){
        return new Course(courseTitle);
    }
}
