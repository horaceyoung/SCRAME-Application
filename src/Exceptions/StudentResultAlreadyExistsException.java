package Exceptions;

public class StudentResultAlreadyExistsException extends Exception {
    public StudentResultAlreadyExistsException(){
        super("StudentResultAlreadyExistsException: This student already had a result record for this course.\n");
    }
}
