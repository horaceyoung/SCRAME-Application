package Exceptions;

public class StudentNotRegisteredForTheCourse extends Exception {
    public StudentNotRegisteredForTheCourse(){
        super("StudentNotRegisteredForTheCourse: The student you have entered has not registered for this course ");
    }
}
