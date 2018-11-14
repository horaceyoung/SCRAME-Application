package SCRAME_grp1;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class representing a university student. The student have essential attributes such as name and matriculation number.'
 * The student class contains the courses that a particular student has registered and the assessment component results.
 */
public class Student implements java.io.Serializable, People
{
	/**
	 * The name of the student
	 */
	private String studentName;
	/**
	 * The matriculation number of the student
	 */
	private String marticNumber;
	/**
	 * A hashmap the maps a course that the student has registered and all of its assessment components
	 */
	private HashMap<String, ArrayList<AssessmentComponent>> courseAndResult = new HashMap<>();

	/**
	 * The constructor of the student class
	 * @param studentName The name of the student
	 * @param marticNumber The matriculation number of the student
	 */
	public Student(String studentName,String marticNumber){
	    this.studentName = studentName;
	    this.marticNumber = marticNumber;
	}

	/**
	 * Get the matriculation number of the student
	 * @return The string representing the matriculation number of the student
	 */
    public String getMatricNumber(){
        return this.marticNumber;
    }

	/**
	 * Get the name of the student
	 * @return The string representing the name of the student
	 */
	public String getName(){
        return studentName;
        }

	/**
	 * Get the all the courses that the student has registered and all of their corresponding assessment components
	 * @return A hashmap that maps the course title of a course with all of its assessment components
	 */
	public HashMap<String,ArrayList<AssessmentComponent>> getCourseAndResult(){
			return courseAndResult;
	}

    /**
     * Override the method setName of interface human
     * @param name The name of the student
     */
	@Override
	public void setName(String name) {
		studentName=name;
	}
}
	
