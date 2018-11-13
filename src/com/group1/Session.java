package com.group1;
import Exceptions.NameNotValidException;
import Exceptions.TutorialLabNameInvalidException;
import Exceptions.TutorialLabNumberInvalidException;
import Exceptions.WeightageNotValidException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A serializable parent class for all kind of course sessions such as tutorial lab or seminar
 */
public class Session implements java.io.Serializable{
    /**
     * the name of the session
     */
    private String sessionName;
    /**
     * vacancy of the seesion
     */
    private int totalVacancy;
    /**
     * An arraylist of all student registered to have this ession
     */
    private ArrayList<Student> registeredStudent = new ArrayList<>();

    /**
     * Constructing class using its name
     * @param sessionName assign the name of the calss
     */

    public Session(String sessionName){
        this.sessionName = sessionName;
    }

    /**
     * constructing the class using its name and vacancy
     * @param sessionName assigning the name of it
     * @param totalVacancy assigning the vancancy of it
     */

    public Session(String sessionName, int totalVacancy){
        this.sessionName = sessionName;
        this.totalVacancy = totalVacancy;
    }

    /**
     * Gets the arraylist of student registered to it
     * @return the arraylist of student object
     */
    public ArrayList<Student> getRegisteredStudent(){return registeredStudent;}

    /**
     * Get the name of it
     * @return the name string
     */

    public String getName(){
        return sessionName;
    }

    /**
     * Get the current vancancy of it
     * @return the current vancancy number
     */

    public int getTotalVacancy(){ return totalVacancy; }

    /**
     * Check whether the session still have vancancy
     * @return treu of false
     */

    public boolean haveVacancy(){
        if(registeredStudent.size()<totalVacancy)
            return true;
        return false;
    }
}
