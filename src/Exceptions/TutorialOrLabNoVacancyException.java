package Exceptions;

public class TutorialOrLabNoVacancyException extends Exception{
    public TutorialOrLabNoVacancyException(){
        super("TutorialOrLabNoVacancyException: The tutorial or lab you have chosen has no vacancy.\n");
    }
}
