package com.group1;

public class InputManager {
    public static boolean ValidateNameInput(String str){
        if (str.matches("[a-zA-Z]+$")) {
            return true;
        }
        else return false;
    }
}
