package com.group1;

public class Validation
{
    //checking if exist, checking if input is legit, all checking method....

    public static boolean studentExists(String matricNumber, DataContainer container){

        for(Student student:container.getStudentsList()){
            if(matricNumber.toUpperCase().equals(student.getMatricNumber()))
                return true;
        }
        return false;
    }
    public static boolean CheckCourseExisted(String courseCode, DataContainer container){
        for ( Course course:container.getCourseList()){
            if (course.GetCourseTitle().equals(courseCode.toUpperCase()))
                return true;
        }
        return false;
    }
    public static boolean CheckTutExisted(String courseName, String tutGroupName, DataContainer container){
        for ( Course currCourse:container.getCourseList()){
            if (currCourse.GetCourseTitle().equals(courseName.toUpperCase())) {
                for (Tutorial currTut: currCourse.GetTutorialList()){
                    if(currTut.GetName().equals(tutGroupName.toUpperCase()))
                        return true;
                }
            }
        }
        return false;
    }

    public static boolean CheckLabExisted(String courseName, String labGroupName, DataContainer container){
        for ( Course currCourse:container.getCourseList()){
            if (currCourse.GetCourseTitle().equals(courseName.toUpperCase())) {
                for (Lab currLab: currCourse.GetLabList()){
                    if(currLab.GetName().equals(labGroupName.toUpperCase()))
                        return true;
                }
            }
        }
        return false;
    }





    public static boolean ValidateNumberInput(String str){
        try{
            int x = Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }

    }


    public static boolean ValidateWeightageInput(String str){
        try{
            float x = Float.parseFloat(str);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
    public static boolean ValidateWeightageSum(float f)
    {
        if (f == 1)
            return true;
        else
            return false;
    }

    public static boolean ValidateNameInput(String str){
        if (str.matches("[a-zA-Z\\s]*")) {
            return true;
        }
        else return false;
    }

    public static boolean ValidateGroupNameInput(String str){
        if (str.matches("[a-zA-Z0-9]*")) {
            return true;
        }
        else return false;
    }

}
