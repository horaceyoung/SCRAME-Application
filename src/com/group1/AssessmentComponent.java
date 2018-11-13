package com.group1;

/**
 *  A Serializable class of a type of assessment for a course
 */

public class AssessmentComponent implements java.io.Serializable{
    /**
     *  A float between 0 - 1 representing the weightage of the assessment
     */

    private float weightage;

    /**
     *  A float between 0 and 100 representing the result of the assessment
     */
    private float result = -1;

    /**
     *  A string representing the type of the assessment
     */
    private String assessmentType;

    /**
     *  Constructing the class by inputing its attributes' value
     * @param weightage Assigning the weightage of assessment
     * @param assessmentType Assigning the type of assessment
     */

    public AssessmentComponent(float weightage, String assessmentType){
        this.weightage = weightage;
        this.assessmentType = assessmentType;
    }

    /**
     * Constructing the class by duplicating an existing AssessmentComponent class
     * @param another The class to duplicate
     */


    public AssessmentComponent(AssessmentComponent another)
    {
        this.weightage = another.weightage;
        this.result = another.result;
        this.assessmentType = another.assessmentType;
    }

    /**
     *  A method that gets the weightage of the assessment
     * @return the value of weightage attribute
     */

    public float getWeightage(){return weightage;}

    /**
     * A method that gets the result of the assessment
     * @return the value of result attribute in float
     */
    public float getResult() {return result;}

    /**
     * A method that gets the type of the assessment
     * @return the value of assessmentType attribute in float
     */
    public String getAssessmentType(){return assessmentType;}

    /**
     * A method that sets the result of the assessment
     * @param result the value of the result to be set
     */
    public void setResult(float result)
    {
        this.result = result;
    }

}
