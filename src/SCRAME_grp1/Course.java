package SCRAME_grp1;

import Exceptions.NameNotValidException;
import Exceptions.TutorialLabNameInvalidException;
import Exceptions.TutorialLabNumberInvalidException;
import Exceptions.WeightageNotValidException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A serializable class that comprehends all relevant information for a course
 */
public class Course implements java.io.Serializable{

    /**
     * A string representing the title of the course
     */
    private String courseTitle;
    /**
     * A coordinator in charge of this course
     */
    private Coordinator coordinator;
    /**
     * An arraylist of main assessment component ( exam and coursework)
     */
    private ArrayList<AssessmentComponent> components = new ArrayList<>();

    /**
     * An arraylist of sub-components of coursework
     */
    private ArrayList<AssessmentComponent> subcomponents = new ArrayList<>();

    /**
     * An arraylist of all tutorials for the course
     */
    private ArrayList<Tutorial> tutorialGroups = new ArrayList<>();
    /**
     * An arraylsit of all labs for the course
     */
    private ArrayList<Lab> labGroups = new ArrayList<>();
    /**
     * An arraylist of all students that are registered to this course
     */
    private ArrayList<Student> studentList = new ArrayList<>();


    /**
     * Constructing the course by entering the tile fo the course
     * @param courseTitle Assigning the title of the course
     */
    public Course(String courseTitle){
        this.courseTitle = courseTitle;
    }

    /**
     * A method to assign the coordinator of the course
     * @param dataContainer the general class that contains everything
     */

    public void AssignCoordinator(DataContainer dataContainer){
        Scanner in = new Scanner(System.in);
        boolean profFound = false;
        try {
            // Get Coordinator Information From user
            System.out.println("Assign the coordinator of the course: " + courseTitle + ". Please Input the name of the coordinator: ");
            String coordinatorName = in.nextLine();

            if(!Validation.validateNameInput(coordinatorName)) // If the input is not valid, throws exception
                throw new NameNotValidException();

            for (Professor prof : dataContainer.getProfessors()){
                if (prof.name.equals(coordinatorName)){
                    profFound = true;
                    coordinator = new Coordinator(prof.name, prof.school);
                }
            }

            if(!profFound){
                throw new Exception("Creating Course: The professor is not found!");
            }

            System.out.println("Assign Coordinator Success: Coordinator " + coordinator.getCoordinatorName() + " of school " + coordinator.getCoordinatorSchool()
                    +" has been assigned to course: " + courseTitle +".");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            AssignCoordinator(dataContainer); // Call the function again to perform the task
        }
    }

    /**
     * A method that adds a tutorial or lab to the respective arraylist.
     * @param type A tutorial or a lab
     */

    public void AddTutorialLabGroups(String type){
        Scanner in = new Scanner(System.in);
        try{
            System.out.println("Add "+ type + ": Please input the total number of " + type + " to add.");
            String rawNumTutorialGroups = in.nextLine();
            int numTutorialGroups;

            if(Validation.validateNumberInput(rawNumTutorialGroups)){ // If the parse result returns true, AKA the input is a valid integer, otherwise throw exception in else block
                numTutorialGroups = Integer.parseInt(rawNumTutorialGroups);
                for(int i = 0 ; i < numTutorialGroups ; i++){
                    System.out.println("Add " + type + " NO." + String.valueOf(i+1) + ". Please input the name of the "+ type + "Group: ");
                    String GroupName = in.nextLine();

                    if (!Validation.validateGroupNameInput(GroupName))
                        throw new TutorialLabNameInvalidException();

                    System.out.println("Add " + type + " NO." + String.valueOf(i+1) + ". Please input the vacancy of the "+ type + " Group: ");
                    String rawTutorialVacancy = in.nextLine();

                    int vacancy;
                    if(Validation.validateNumberInput(rawTutorialVacancy)){
                        vacancy = Integer.parseInt(rawTutorialVacancy);
                        // The following block works differently for Tutorial and Lab
                        if(type.equals("Tutorial")) {
                            Tutorial newSession = new Tutorial(GroupName, vacancy);
                            tutorialGroups.add(newSession);
                            System.out.println("Add " + type + " No." + String.valueOf(i+1)+ " Success: " + "The tutorial group name is "+
                                    newSession.getName() + " and the vacancy is " + String.valueOf(newSession.getTotalVacancy())+". ");
                        }
                        else if(type.equals("Lab")){
                            Lab newSession = new Lab(GroupName, vacancy);
                            labGroups.add((newSession));
                            System.out.println("Add " + type + " No." + String.valueOf(i+1)+ " Success: " + "The lab group name is "+
                                    newSession.getName() + " and the vacancy is " + String.valueOf(newSession.getTotalVacancy())+". ");
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
            AddTutorialLabGroups(type);
        }
        catch (TutorialLabNameInvalidException e){
            System.out.println(e.getMessage());
            AddTutorialLabGroups(type);
        }
    }

    /**
     * A method that assign a type of assessment to respective arraylist
     * @param components A main component or a sub component
     * @param assessmenttype The name of the component
     */

    public void assignComponentWeightage(ArrayList<AssessmentComponent> components, String assessmenttype){
        Scanner in = new Scanner(System.in);
        System.out.println("Assign Components and Weightages:\nPlease input the weightage of the " + assessmenttype + " : (a float number between 0-1)");
        String rawWeightage = in.nextLine();
        try{
            if(!Validation.validateFloatInput(rawWeightage) ||  Float.parseFloat(rawWeightage ) <= 0 || Float.parseFloat(rawWeightage ) >= 1){
                throw new WeightageNotValidException();
            }
            float examWeightage = Float.parseFloat(rawWeightage);
            AssessmentComponent newComponent = new AssessmentComponent(examWeightage, assessmenttype);
            components.add(newComponent);
            System.out.println("Assign " + assessmenttype + " Weightage Successful\n");
        }
        catch (WeightageNotValidException e){
            System.out.println(e.getMessage());
            assignComponentWeightage(components, assessmenttype);
        }

    }

    /**
     * Get the title of the course
     * @return the tile fo course in string
     */


    public String getCourseTitle(){
        return courseTitle;
    }

    /**
     * Get the list of tutorial the course has
     * @return an arraylist of tutorials
     */
    public ArrayList<Tutorial> getTutorialList(){return tutorialGroups;}

    /**
     * Get the list of lab the course has
     * @return an arraylist of labs
     */
    public ArrayList<Lab> getLabList(){return labGroups;}

    /**
     * Get the main assessment component of the course
     * @return an arraylist of component
     */
    public ArrayList<AssessmentComponent> getComponents(){return components;}

    /**
     * Get the sub assessment component of the coursework
     * @return an arraylist of component
     */
    public ArrayList<AssessmentComponent> getSubComponents(){return subcomponents;}

    /**
     * Get all the students registered to this course
     * @return an arraylist of students
     */
    public ArrayList<Student> getStudentList(){ return studentList;}

    /**
     * Check whether the course has tutorial or not
     * @return  true or flase
     */

    public boolean haveTutorial(){
        if(this.tutorialGroups.isEmpty())
            return false;
        else return true;
    }

    /**
     * Check whether the course has lab or not
     * @return  true or false
     */

    public boolean haveLab(){
        if(this.labGroups.isEmpty())
            return false;
        else return true;
    }

}
