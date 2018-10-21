package com.group1;

import java.util.ArrayList;

public class AssessmentComponent {
    private float weightage;
    private float result = -1;
    private String assessmentType;

    public AssessmentComponent(float weightage, String assessmentType){
        this.weightage = weightage;
        this.assessmentType = assessmentType;
    }

    public float getWeightage(){return weightage;}
}
