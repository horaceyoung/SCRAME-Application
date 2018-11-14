package Exceptions;

import SCRAME_grp1.Course;
import SCRAME_grp1.Student;

public class StudentAlreadyRegisteredForThisCourseException extends Exception{
    public StudentAlreadyRegisteredForThisCourseException(Student student, Course course){
        super("StudentAlreadyRegisteredForThisCourseException: "+ "The student "+student.getMatricNumber()+" has already registered for this course "+course.getCourseTitle());
    }
}
