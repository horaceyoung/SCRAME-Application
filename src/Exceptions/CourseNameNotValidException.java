package Exceptions;

public class CourseNameNotValidException extends NameNotValidException{
    public CourseNameNotValidException(){
        super("CourseNameNotValidException: The name of the course is invalid. You should only include alphabets and numbers, not spaces.\n");
    }
}
