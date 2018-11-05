package Exceptions;

import com.group1.Course;
import com.group1.Student;

public class StudentAlreadyRegisteredForThisCourseException extends Exception{
    public StudentAlreadyRegisteredForThisCourseException(Student student, Course course){
        super("StudentAlreadyRegisteredForThisCourseException: "+ "The student "+student.getMatricNumber()+" has already registered for this course "+course.GetCourseTitle());
    }
}
