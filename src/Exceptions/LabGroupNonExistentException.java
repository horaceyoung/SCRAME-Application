package Exceptions;

public class LabGroupNonExistentException extends Exception {
    public LabGroupNonExistentException(){
        super("LabGroupNonExistentException: The lab group you have chosen is not existent. Please try again.\n");
    }
}
