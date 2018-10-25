package Exceptions;

public class TutorialGroupNonExistentException extends Exception {
    public TutorialGroupNonExistentException()
    {
        super("TutorialGroupNonExistentException: The tutorial group you have chosen is not existent. Please try again.\n");
    }
}
