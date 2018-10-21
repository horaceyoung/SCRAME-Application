package Exceptions;

public class CourseNotFoundException extends Exception
{
    public  CourseNotFoundException()
    {
        super("CourseNotFoundException: The course name you entered is not in our database, please create this course first.\n");
    }
}
