package com.group1;

import Exceptions.NameNotValidException;
import Exceptions.TutorialLabNumberInvalidException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Course {
    private String courseTitle;
    private Coordinator coordinator;
    private ArrayList<AssessmentComponent> components;
    private ArrayList<AssessmentComponent> subcomponents;
    private ArrayList<Tutorial> tutorialGroups;
    private ArrayList<Lab> labGroups;
    private int maxTutorialsNumber = 20;
    private int maxLabsNumber = 20;

    public Course(String courseTitle){
        this.courseTitle = courseTitle;
    }

    public void AssignCoordinator(){
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Assign the coordinator of the course: " + courseTitle + ". Please Input the name of the coordinator: ");
            String coordinatorName = in.nextLine();
            if(!InputManager.ValidateNameInput(coordinatorName))
                throw new NameNotValidException();
            System.out.println("Assign the coordinator of the course: " + courseTitle + ". Please Input the School of the coordinator: ");
            String coordinatorSchool = in.nextLine();
            if(!InputManager.ValidateNameInput(coordinatorSchool))
                throw new NameNotValidException();
            this.coordinator = new Coordinator(coordinatorName, coordinatorSchool);
            System.out.println("Assign Coordinator Success: Coordinator " + coordinator.getCoordinatorName() + " of school " + coordinator.getCoordinatorSchool()
                    +" has been assigned to course: " + courseTitle +".");
        }
        catch (NameNotValidException e){
            System.out.println(e.getMessage());
            AssignCoordinator();
        }
    }

    public void AddTutorialGroups(){
        Scanner in = new Scanner(System.in);
        try{
            String rawNumTutorialGroups = in.nextLine();
            int numTutorialGroups;
            if(InputManager.ValidateNumberInput(rawNumTutorialGroups)){
                numTutorialGroups = Integer.parseInt(rawNumTutorialGroups);
                for(int i = 0 ; i < numTutorialGroups ; i++){
                    System
                }
            }
            else{
                throw new TutorialLabNumberInvalidException();
            }
        }
        catch (TutorialLabNumberInvalidException e){
            e.getMessage();
            AddTutorialGroups();
        }
    }

    public void AssignComponentWeightage(){
        Scanner in = new Scanner(System.in);
    }
}
