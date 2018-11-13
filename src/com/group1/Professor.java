package com.group1;

/**
 * Profesor is the class of people who are eligible to be coordinator for courses
 */

public class Professor implements java.io.Serializable,People {
    /**
     * The name of the professor
     */
    public String name;
    /**
     * The school which the professor belongs to
     */
    public String school;

    /**
     * The constructor to create a professor object
     * @param name the name of the professor
     * @param school the school which the profesor belongs to
     */
    public Professor(String name, String school){
        this.name = name;
        this.school = school;
    }

    /**
     * Acquire the name of the professor
     * @return the name of the professor as a string
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set the name of the professor
     * @param name the name of the professor
     */
    public void setName(String name) {
        this.name = name;
    }
}
