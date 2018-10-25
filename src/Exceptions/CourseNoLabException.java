package Exceptions;

public class CourseNoLabException extends Exception {
    public CourseNoLabException(){
        super("CourseNoTutorialException: The course does not have Lab.\n");
    }
}
