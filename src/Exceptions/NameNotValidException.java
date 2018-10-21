package Exceptions;

public class NameNotValidException extends Exception {
    public NameNotValidException(){
        super("NameNotValidException: The name you have inputted is not valid.The name should contain alphabetic characters only.\n");
    }

    public NameNotValidException(String message){
        super(message);
    }

}
