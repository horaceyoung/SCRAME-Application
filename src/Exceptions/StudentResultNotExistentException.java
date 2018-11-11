package Exceptions;

import com.group1.Course;
import com.group1.Student;

public class StudentResultNotExistentException extends Exception {
    public StudentResultNotExistentException(Student student, Course course){
        super("StudentResultNotExistentException: The student "+student.getName()+" has no result records in this course "+ course.getCourseTitle());
    }

}
