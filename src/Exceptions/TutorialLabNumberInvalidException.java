package Exceptions;

public class TutorialLabNumberInvalidException extends Exception{
    public TutorialLabNumberInvalidException(){
        super("TutorialLabNumberInvalidException: The total number of Tutorials/Labs is not valid. The range should be in (0-20)");
    }
}
