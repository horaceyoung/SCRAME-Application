package Exceptions;

public class CourseNoExamComponentException extends NameNotValidException
{
    public CourseNoExamComponentException()
    {
        super("CourseNoExamComponentException: The course you entered are yet to enter its assessment component weightage.\nPlease enter the assesment component weightage first.\n");
    }
}

