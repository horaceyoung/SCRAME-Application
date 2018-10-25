package Exceptions;

public class TutorialLabNotAvailableException extends NameNotValidException{
    public TutorialLabNotAvailableException(){
        super("TutorialLabNotAvailableException: There are no Lab or Tutorial Group available to register.\n");
    }
}
