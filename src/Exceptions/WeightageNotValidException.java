package Exceptions;

public class WeightageNotValidException extends Exception {
    public WeightageNotValidException(){
        super("WeightageNotValidException: The weightage you have entered is not valid. It should be a number between 0-1.");
    }
}
