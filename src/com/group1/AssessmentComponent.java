package com.group1;

import java.util.ArrayList;

public class AssessmentComponent implements java.io.Serializable{
    private float weightage;
    private float result = -1;
    private String assessmentType;

    public AssessmentComponent(float weightage, String assessmentType){
        this.weightage = weightage;
        this.assessmentType = assessmentType;
    }

    public float getWeightage(){return weightage;}
    public float getResult() {return result;}
    public String getAssessmentType(){return assessmentType;}
}
