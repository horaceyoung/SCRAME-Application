package com.group1;

import javax.xml.crypto.Data;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReadingManager
{

    public ArrayList<Tutorial> GetCourseTutorials(String courseTitle, DataContainer dataContainer){
        ArrayList<Tutorial> tutorialList = new ArrayList<>();
        for(Course course:dataContainer.getCourseList()){
            if(courseTitle.equals(course.GetCourseTitle()))
                tutorialList = course.GetTutorialList();

        }
        return tutorialList;
    }

    public ArrayList<Lab> GetCourseLabs(String courseTitle, DataContainer dataContainer){
        ArrayList<Lab> labList = new ArrayList<>();
        for(Course course:dataContainer.getCourseList()){
            if(courseTitle.equals(course.GetCourseTitle()))
                labList = course.GetLabList();

        }
        return labList;
    }





}
