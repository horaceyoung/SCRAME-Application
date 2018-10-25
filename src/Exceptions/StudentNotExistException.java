package Exceptions;

public class StudentNotExistException extends Exception {
    public StudentNotExistException(){
        super("StudentNotExistException: The student you are inquiring does not exist. Please check your input.");
    }
}
