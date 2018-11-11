package Exceptions;

import com.group1.Course;
import com.group1.Student;

public class StudentResultNotExistentException extends Exception {
    public StudentResultNotExistentException(Student student, Course course){
<<<<<<< HEAD
        super("StudentResultNotExistentException: The student "+student.getName()+" has no result records in this course "+ course.getCourseTitle());
=======
        super("StudentResultNotExistentException: The student "+student.GetStudentName()+" has no result records in this course. Please add exam results first."+ course.getCourseTitle());
>>>>>>> a4991a1b4d2351ecd2f8bb0fe63db0f105609121
    }

}
