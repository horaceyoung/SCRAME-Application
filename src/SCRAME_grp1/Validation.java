package SCRAME_grp1;

import java.util.ArrayList;

/**
 * A controller class in charge of validating user input and verifying logical relationships among different class
 */

public class Validation
{
    //checking if exist, checking if input is legit, all checking method....

    /**
     * A method that checks the existence of student record from DataContainer
     * @param matricNumber The matriculation number of student to be checked
     * @param container A datacontainer that contains the student and the course
     * @return Return true if the student with the matriculation number is existed in the DataContainer, else it returns false
     */
    public static boolean studentExists(String matricNumber, DataContainer container){
        if(container.getStudentsList().isEmpty())
            return false;
        for(Student student:container.getStudentsList()){
            if(matricNumber.toUpperCase().equals(student.getMatricNumber()))
                return true;
        }
        return false;
    }

    /**
     * A method that checks the existence of course record from DataContainer
     * @param courseCode The course title of the course which the user wish to inquire
     * @param container A datacontainer that contains the student and the course
     * @return Return true if the course with the course code is existed in the DataContainer, else it returns false
     */
    public static boolean checkCourseExisted(String courseCode, DataContainer container){

        if(container.getCourseList().isEmpty())
            return false;
        for ( Course course:container.getCourseList()){
            if (course.getCourseTitle().equals(courseCode.toUpperCase()))
                return true;
        }
        return false;
    }


    /**
     * A methods that checks the student results record of course titles
     * @param student The student which the user wish to inquire
     * @param coursetitle The course which the user wish to inquire
     * @return Returns the list of student with the course code
     */
    public static int checkStudentResultsRecord(Student student, String coursetitle)
    {
        for(String key:student.getCourseAndResult().keySet())
        {
            if (key.equals(coursetitle))
            {
                ArrayList<AssessmentComponent> listtemp = student.getCourseAndResult().get(key);
                return listtemp.size();
            }
        }

        return 0;

    }

    /**
     * A method that checks whether student registered for course
     * @param student The student which the user wish to inquire
     * @param coursetitle The course which the user wish to inquire
     * @return Returns true if the student has registered for the course that matches the course title
     */
    public static Boolean checkWhetherStudentRegisteredForACourse(Student student, String coursetitle)
    {
        for(String key:student.getCourseAndResult().keySet())
        {
            if (key.equals(coursetitle))
                return true;
        }
        return false;
    }

    /**
     * A Boolean method that check whether a course has assessment weightage assigned
     * @param course The course which the user wish to inquire
     * @return Returns false if the assessment weightage is not assigned to the course with the course code, else it will return true
     */
    public static Boolean checkWhetherHasAssessmentWeightage(Course course)
    {
        if (course.getComponents().isEmpty())
            return false;
        else
            return true;
    }


    /**
     * A Boolean method that check whether the format of user input is valid
     * @param str The input string from the user
     * @return Returns true if the input is a valid integer, else it will raise NumberFormatException by returning false
     */
    public static boolean validateNumberInput(String str){
        try{
            int x = Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }

    }

    /**
     * A Boolean method that check whether the format of user input is valid
     * @param str The input string from the user
     * @return Returns true if the input is a valid float, else it will raise NumberFormatException by returning false
     */
    public static boolean validateFloatInput(String str){
        try{
            float x = Float.parseFloat(str);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * A Boolean method that check whether the sum of the component weightage is valid
     * @param f the sum of the floating number from user input
     * @return Returns true if the sum of the floating number from user input is 1, else it returns false
     */
    public static boolean validateWeightageSum(float f)
    {
        if (f == 1)
            return true;
        else
            return false;
    }

    /**
     * A Boolean method that check whether the name input string from the user is valid
     * @param str The input string from the user
     * @return Returns true if the input string is in the range of alphabetical A to Z, either in uppercases or lowercases, or both
     */
    public static boolean validateNameInput(String str){
        if (str.matches("[a-zA-Z\\s]*")) {
            return true;
        }
        else return false;
    }

    /**
     * A Boolean method that check whether the group name input string from the user is valid
     * @param str The input string from the user
     * @return Returns true if the input string is in the range of alphabetical A to Z, either in uppercases or lowercases, or both, or in the range from 0 to 9
     */
    public static boolean validateGroupNameInput(String str){
        if (str.matches("[a-zA-Z0-9]*")) {
            return true;
        }
        else return false;
    }

}
