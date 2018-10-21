package Exceptions;

public class WeightageSumNotValidException extends Exception
{
    public WeightageSumNotValidException(){
        super("WeightageNotValidException: The weightage you have entered is not valid. It should sums up to 1\n");
    }
}
