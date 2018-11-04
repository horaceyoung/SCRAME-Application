package Exceptions;

public class CourseNoVacancyException extends Exception {
    public CourseNoVacancyException(){
        super("CourseNoVacancyException: The course you have chosen has no vacancy.\n");
    }
}
