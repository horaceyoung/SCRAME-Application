package com.group1;

import Exceptions.NameNotValidException;
import Exceptions.TutorialLabNameInvalidException;
import Exceptions.TutorialLabNumberInvalidException;
import Exceptions.WeightageNotValidException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Course {
    private String courseTitle;
    private Coordinator coordinator;
    private ArrayList<AssessmentComponent> components = new ArrayList<>();
    private ArrayList<AssessmentComponent> subcomponents = new ArrayList<>();
    private ArrayList<Tutorial> tutorialGroups = new ArrayList<>();
    private ArrayList<Lab> labGroups = new ArrayList<>();
    private int maxTutorialsNumber = 20;
    private int maxLabsNumber = 20;

    public Course(String courseTitle){
        this.courseTitle = courseTitle;
    }

    public void AssignCoordinator(){
        Scanner in = new Scanner(System.in);
        try {
            // Get Coordinator Information From user
            System.out.println("Assign the coordinator of the course: " + courseTitle + ". Please Input the name of the coordinator: ");
            String coordinatorName = in.nextLine();

            if(!InputManager.ValidateNameInput(coordinatorName)) // If the input is not valid, throws exception
                throw new NameNotValidException();
            System.out.println("Assign the coordinator of the course: " + courseTitle + ". Please Input the School of the coordinator: ");

            String coordinatorSchool = in.nextLine();
            if(!InputManager.ValidateNameInput(coordinatorSchool)) // If the input is not valid, throws exception
                throw new NameNotValidException();

            this.coordinator = new Coordinator(coordinatorName, coordinatorSchool);
            System.out.println("Assign Coordinator Success: Coordinator " + coordinator.getCoordinatorName() + " of school " + coordinator.getCoordinatorSchool()
                    +" has been assigned to course: " + courseTitle +".");
        }
        catch (NameNotValidException e){
            System.out.println(e.getMessage());
            AssignCoordinator(); // Call the function again to perform the task
        }
    }

    public void AddTutorialLabGroups(String Type){
        Scanner in = new Scanner(System.in);
        String addType = Type; // addType can be either "Tutorial" or "Lab"
        try{
            System.out.println("Add "+ addType + ": Please input the total number of " + addType + " to add.");
            String rawNumTutorialGroups = in.nextLine();
            int numTutorialGroups;

            if(InputManager.ValidateNumberInput(rawNumTutorialGroups)){ // If the parse result returns true, AKA the input is a valid integer, otherwise throw exception in else block
                numTutorialGroups = Integer.parseInt(rawNumTutorialGroups);
                for(int i = 0 ; i < numTutorialGroups ; i++){
                    System.out.println("Add " + Type + " NO." + String.valueOf(i+1) + ". Please input the name of the "+ Type + "Group: ");
                    String GroupName = in.nextLine();

                    if (!InputManager.ValidateGroupNameInput(GroupName))
                        throw new TutorialLabNameInvalidException();

                    System.out.println("Add " + Type + " NO." + String.valueOf(i+1) + ". Please input the vacancy of the "+ Type + " Group: ");
                    String rawTutorialVacancy = in.nextLine();

                    int vacancy;
                    if(InputManager.ValidateNumberInput(rawTutorialVacancy)){
                        vacancy = Integer.parseInt(rawTutorialVacancy);
                        // The following block works differently for Tutorial and Lab
                        if(addType.equals("Tutorial")) {
                            Tutorial newSession = new Tutorial(GroupName, vacancy);
                            tutorialGroups.add(newSession);
                            System.out.println("Add " + Type + " No." + String.valueOf(i+1)+ " Success: " + "The tutorial group name is "+
                                    newSession.GetName() + " and the vacancy is " + String.valueOf(newSession.GetTotalVacancy())+". ");
                        }
                        else if(addType.equals("Lab")){
                            Lab newSession = new Lab(GroupName, vacancy);
                            labGroups.add((newSession));
                            System.out.println("Add " + Type + " No." + String.valueOf(i+1)+ " Success: " + "The lab group name is "+
                                    newSession.GetName() + " and the vacancy is " + String.valueOf(newSession.GetTotalVacancy())+". ");
                        }
                    }
                    else{
                        throw new TutorialLabNumberInvalidException();
                    }
                }
            }
            else{
                throw new TutorialLabNumberInvalidException();
            }
        }
        // Handles the exception and call the function again
        catch (TutorialLabNumberInvalidException e){
            System.out.println(e.getMessage());
            AddTutorialLabGroups(Type);
        }
        catch (TutorialLabNameInvalidException e){
            System.out.println(e.getMessage());
            AddTutorialLabGroups(Type);
        }
    }

    public void AssignComponentWeightage(ArrayList<AssessmentComponent> components, String assessmentType){
        Scanner in = new Scanner(System.in);
        System.out.println("Assign Components and Weightages:\nPlease input the weightage of the " + assessmentType + " : (a float number between 0-1)");
        String rawExamWeightage = in.nextLine();
        try{
            if(!InputManager.ValidateWeightageInput(rawExamWeightage) ||  Float.parseFloat(rawExamWeightage ) == 0){
                throw new WeightageNotValidException();
            }
            float examWeightage = Float.parseFloat(rawExamWeightage);
            AssessmentComponent newComponent = new AssessmentComponent(examWeightage, assessmentType);
            components.add(newComponent);
            System.out.println("Assign " + assessmentType + " Weightage Successful\n");
        }
        catch (WeightageNotValidException e){
            System.out.println(e.getMessage());
            AssignComponentWeightage(components, assessmentType);
        }

    }

    public String GetCourseTitle(){
        return courseTitle;
    }
    public Coordinator GetCoordinator(){return coordinator;}
    public ArrayList<Tutorial> GetTutorialList(){return tutorialGroups;}
    public ArrayList<Lab> GetLabList(){return labGroups;}
    public ArrayList<AssessmentComponent> GetComponents(){return components;}
    public ArrayList<AssessmentComponent> GetSubComponents(){return subcomponents;}
}
