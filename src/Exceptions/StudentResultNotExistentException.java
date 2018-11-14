package Exceptions;

import SCRAME_grp1.Course;
import SCRAME_grp1.Student;

public class StudentResultNotExistentException extends Exception {
    public StudentResultNotExistentException(Student student, Course course){
        super("StudentResultNotExistentException: The student "+student.getName()+" has no result records in this course. Please add exam results first."+ course.getCourseTitle());
    }

}
