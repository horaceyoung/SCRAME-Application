package Exceptions;

public class TutorialLabNameInvalidException extends NameNotValidException {
    public TutorialLabNameInvalidException(){
        super("TutorialLabNumberInvalidException: The name of the tutorial group is invalid. You should only include alphabets and numbers, not spaces.\n");
    }
}
