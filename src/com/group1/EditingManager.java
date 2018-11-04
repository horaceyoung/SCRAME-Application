package com.group1;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class EditingManager
{
    public void RegisterStudentToCourseLecture(String matric, String courseTitle, DataContainer dataContainer) {
        ArrayList<Course> courseList = dataContainer.getCourseList();
        for(Course course : courseList){
            if(courseTitle.equals(course.GetCourseTitle())){
                course.
            }
        }


    }
}
