package com.group1;

public class Validation
{
    //checking if exist, checking if input is legit, all checking method....

    public static boolean studentExists(String matricNumber, DataContainer container){

        for(Student student:container.getStudentsList()){
            if(matricNumber.equals(student.getMatricNumber()))
                return true;
        }
        return false;
    }
    public static boolean CheckCourseExisted(String courseCode, DataContainer container){
        for ( Course course:container.getCourseList()){
            if (course.GetCourseTitle().equals(courseCode))
                return true;
        }
        return false;
    }
}
