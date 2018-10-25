package Exceptions;

public class CourseNoTutorialException extends Exception
{
    public  CourseNoTutorialException()
    {
        super("CourseNoTutorialException: The course does not have tutorial.\n");
    }
}
