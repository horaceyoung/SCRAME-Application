package com.group1;

import java.util.ArrayList;
import java.util.HashMap;

public class Student implements java.io.Serializable, People
{
	private String studentName;
	private String marticNumber;
	private HashMap<String, ArrayList<AssessmentComponent>> courseAndResult = new HashMap<>();
	
	public Student(String studentName,String marticNumber){
	    this.studentName = studentName;
	    this.marticNumber = marticNumber;
	}
    public String getMatricNumber(){
        return this.marticNumber;
    }
	public String getName(){
        return studentName;
        }
<<<<<<< HEAD

    public void setName(String name){
	    this.studentName=name;
    }


=======
>>>>>>> a4991a1b4d2351ecd2f8bb0fe63db0f105609121
	public HashMap<String,ArrayList<AssessmentComponent>> GetCourseAndResult(){
			return courseAndResult;
	}
	public void ClearHashmapValue (String key)
	{
		courseAndResult.get(key).clear();
	}

}
	

