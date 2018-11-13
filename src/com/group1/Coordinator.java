package com.group1;

/**
 * A Serializable class of the coordinator of a course
 */
public class Coordinator implements java.io.Serializable{
    /**
     * A string representing the name of the coordinator
     */
    private String name;
    /**
     * A string representing which school the coordinator belongs to
     */
    private String school;

    /**
     *  Constructing the class by inputing the attribute values
     * @param name Assigning the name of the coordinator
     * @param school Assigning the school of the coordinator
     */
    public Coordinator(String name, String school){
        this.name = name;
        this.school = school;
    }

    /**
     * A method getting the name of the coordinator
     * @return the name
     */

    public String getCoordinatorName(){
        return name;
    }

    /**
     * A method getting the school of the coordinator
     * @return the school name
     */

    public String getCoordinatorSchool(){
        return school;
    }
}
