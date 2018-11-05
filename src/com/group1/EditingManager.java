package com.group1;


import Exceptions.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EditingManager
{
    public void RegisterStudentToCourseLecture(Student thisStudent, String courseTitle, DataContainer dataContainer) {
        ArrayList<Course> courseList = dataContainer.getCourseList();
        ArrayList<Student> courseStudentList = new ArrayList<>();

        for (Course course : courseList) {
            if (courseTitle.equals(course.GetCourseTitle())) {
                courseStudentList=course.GetStudentList();
                courseStudentList.add(thisStudent);
            }
        }

        HashMap<String,ArrayList<AssessmentComponent>> studentCourseList = thisStudent.GetCourseAndResult();
        studentCourseList.put(courseTitle,null);


        System.out.println("Student "+thisStudent.getMatricNumber()+" "+thisStudent.GetStudentName()+" has been registered to Course "+courseTitle);
    }

    public void RegisterStudentToTutorial(Student student, String courseTitle, String tutorialName, DataContainer dataContainer){

        ArrayList<Course> courseList = dataContainer.getCourseList();
        ArrayList<Tutorial> tutorialList = new ArrayList<>();
        Tutorial thisTutorial=null;
        boolean found=false;
        for (Course course : courseList) {
            if (courseTitle.equals(course.GetCourseTitle())) {
                tutorialList = course.GetTutorialList();
            }
        }
        try{
        for(Tutorial tutorial:tutorialList){
            if(tutorialName.equals(tutorial.sessionName)){
                thisTutorial = tutorial;
                found=true;
            }
        }
        if (found==false) throw new TutorialGroupNonExistentException();

        if (!thisTutorial.HaveVacancy())
            throw new TutorialOrLabNoVacancyException();
        thisTutorial.GetRegisteredStudent().add(student);

         System.out.println("Student "+student.getMatricNumber()+" "+student.GetStudentName()+" has been registered to "+ tutorialName+ "of course "+courseTitle);

        }
        catch (TutorialGroupNonExistentException e){
            System.out.println(e.getMessage());
        }
        catch (TutorialOrLabNoVacancyException e){
            System.out.println(e.getMessage());
        }
    }

    public void RegisterStudentToLab(Student student, String courseTitle, String labName, DataContainer dataContainer){

        ArrayList<Course> courseList = dataContainer.getCourseList();
        ArrayList<Lab> labList = new ArrayList<>();
        Lab thisLab=null;
        boolean found = false;
        for (Course course : courseList) {
            if (courseTitle.equals(course.GetCourseTitle())) {
                labList = course.GetLabList();
            }
        }
        try{
            for(Lab lab:labList){
                if(labName.equals(lab.sessionName)){
                    thisLab = lab;
                    found = true;
                }
            }

            if(found ==false)
                throw new LabGroupNonExistentException();

            if(!thisLab.HaveVacancy())
                throw new TutorialOrLabNoVacancyException();
            thisLab.GetRegisteredStudent().add(student);

            System.out.println("Student "+student.getMatricNumber()+" "+student.GetStudentName()+" has been registered to "+ labName+ "of course "+courseTitle);

        }
        catch (LabGroupNonExistentException e){
            System.out.println(e.getMessage());
        }
        catch (TutorialOrLabNoVacancyException e){
            System.out.println(e.getMessage());
        }
    }


    public static void AddCourseComponent(String courtsetitle, DataContainer container) {

        Course newcourse6 = null;
        for ( Course course:container.getCourseList()){
            if (course.GetCourseTitle().equals(courtsetitle))
                newcourse6 = course;
        }

        if (!newcourse6.GetComponents().isEmpty())
        {
            System.out.println("This course's component weightage has already been assigned.");
            return;
        }

        String[] component = {"Exam", "Coursework"};

        while (true) {
            for (String compname : component) {
                newcourse6.AssignComponentWeightage(newcourse6.GetComponents(), compname);
            }
            float weightagesum = 0;
            for (AssessmentComponent componentnew : newcourse6.GetComponents()) {
                weightagesum += componentnew.getWeightage();
            }
            System.out.println(weightagesum);
            if (Validation.ValidateWeightageSum(weightagesum)) {
                break;
            } else {
                System.out.println("The total weightage you have entered is not valid. It should sum up to 1\n");
                newcourse6.ClearComponentWeightage();

            }
        }


        while (true) {
            String temp;
            while (true) {
                System.out.println("Do we have sub-component for coursework? Yes: Enter 1  No: Enter 0");
                Scanner ynscanner = new Scanner(System.in);
                temp = ynscanner.nextLine();
                if (Validation.ValidateNumberInput(temp)) {
                    break;

                } else {
                    System.out.println("Input not valid, please enter 0 or 1\n");
                }


            }

            if (Integer.parseInt(temp) == 0) {
                break;
            }

            while (true) {
                System.out.println("How many sub-components do u have?");
                Scanner numscanner = new Scanner(System.in);
                temp = numscanner.nextLine();
                if (Validation.ValidateNumberInput(temp) && Integer.parseInt(temp) > 1) {
                    break;

                } else {
                    System.out.println("Input not valid, please enter number greater than 1.\n");
                }
            }
            int subcomnum = Integer.parseInt(temp);
            String[] subcoponent = new String[subcomnum];

            for (int x = 1; x <= subcomnum; x++) {
                System.out.println("Please enter sub-component " + x + " name");
                Scanner subcomponentscanner = new Scanner(System.in);
                subcoponent[x - 1] = subcomponentscanner.nextLine();


            }
            while (true) {
                for (String subcomname : subcoponent) {
                    newcourse6.AssignComponentWeightage(newcourse6.GetSubComponents(), subcomname);
                }
                float subweightagesum = 0;
                for (AssessmentComponent componentnew : newcourse6.GetSubComponents()) {
                    subweightagesum += componentnew.getWeightage();
                }
                if (Validation.ValidateWeightageSum(subweightagesum)) {
                    break;
                } else {
                    System.out.println(subweightagesum);
                    System.out.println("The total weightage you have entered is not valid. It should sum up to 1\n");
                    newcourse6.ClearSubComponentWeightage();
                }
            }
            break;


        }



    }

    public static void AssignComponentResults(String matricnumber, String coursetitle, DataContainer container)
    {
        Scanner scanner = new Scanner(System.in);
        Student student = null;
        Course course = null;

        for(Course mycourse: container.getCourseList())
        {
            if (mycourse.GetCourseTitle().equals(coursetitle))
            {
                course = mycourse;
            }
        }
        for(Student mystudent: container.getStudentsList())
        {
            if (mystudent.getMatricNumber().equals(matricnumber))
            {
                student = mystudent;
            }
        }
        try
        {
            if ( !Validation.CheckWhetherStudentRegisteredForACourse(student, coursetitle))
            {
                throw new StudentNotRegisteredForTheCourse();
            }
            else if ( !Validation.CheckWhetherHasAssessmentWeightage(course))
            {
                throw new CourseNoExamComponentException();
            }
            else if (Validation.CheckStudentResultsRecord(student, coursetitle))
            {
                throw new StudentResultAlreadyExistsException();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Press any key to continue.");
            scanner.next();
            return;
        }

        int x = 1;
        int y = 1;
        while (y == 1)
        {
            while (x == 1)
            {
                System.out.println(course.GetComponents().size());
                for(AssessmentComponent component: course.GetComponents())
                {
                    AssessmentComponent newcomponent = new AssessmentComponent(component);
                    if (newcomponent.getAssessmentType().equals("Coursework"))
                    {
                        newcomponent.setResult(-1);
                        System.out.println("I am here");
                        student.GetCourseAndResult().get(coursetitle).add(newcomponent);
                        x = 0;
                        break;
                    }
                    System.out.println("Please enter the student's result for " + newcomponent.getAssessmentType() + ", you should enter a mark between 0-100.");
                    String mark = scanner.next();
                    System.out.println("llalala");
                    if (Validation.ValidateFloatInput(mark) && Float.parseFloat(mark) >= 0 && Float.parseFloat(mark) <= 100)
                    {
                        newcomponent.setResult(Float.parseFloat(mark));
                        System.out.println("hahah");
                        try
                        {
                            student.GetCourseAndResult().get(coursetitle).add(newcomponent);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        System.out.println("lalala");

                        x = 0;
                    }
                    else
                    {
                        System.out.println("MarkNotValidException: The mark you entered for the component is not valid. Please check format. You are going to restart mark entering");
                        student.ClearHashmapValue(coursetitle);
                        x = 1;
                        break;
                    }

                }

            }
            System.out.println("-----------");

            for(AssessmentComponent component: course.GetSubComponents())
            {
                AssessmentComponent newcomponent = new AssessmentComponent(component);

                System.out.println("Please enter the student's result for " + newcomponent.getAssessmentType() + ", you should enter a mark between 0-100.");
                String mark = scanner.nextLine();
                if (Validation.ValidateFloatInput(mark) && Float.parseFloat(mark) >= 0 && Float.parseFloat(mark) <= 100)
                {
                    newcomponent.setResult(Float.parseFloat(mark));
                    student.GetCourseAndResult().get(coursetitle).add(newcomponent);
                    y = 0;
                }
                else
                {
                    System.out.println("MarkNotValidException: The mark you entered for the component is not valid. Please check format. You are going to restart mark entering");
                    student.ClearHashmapValue(coursetitle);
                    x = 1;
                    y = 1;
                    break;
                }

            }
        }

    }

    public static void printTranscript(String studentMatricTranscript, DataContainer dataContainer) throws FileNotFoundException{

        String transcriptOutcome = "Matric No: " + studentMatricTranscript + "\n";
        HashMap<String, ArrayList<AssessmentComponent>> courseAndResult;
        HashMap<String, ArrayList<AssessmentComponent>> currentCourse;
        //read Results file to get results
        for(Student student : dataContainer.getStudentsList()) {
            if (student.getMatricNumber().equals(studentMatricTranscript)){
                transcriptOutcome += "Student Name: " + student.GetStudentName() + "\n";
            }

            courseAndResult = student.GetCourseAndResult();
            for (String key : courseAndResult.keySet()){
                    transcriptOutcome += key + "\n" + "Overall Mark: " + "\n";
                    ArrayList<AssessmentComponent> components = courseAndResult.get(key);
                    for (AssessmentComponent component : components){
                        transcriptOutcome += "\t" + component.getAssessmentType() + " " + component.getWeightage() + " " + component.getResult() + "\n";
                    }
            }
        }
        System.out.println(transcriptOutcome);
    }
    
    
    public static void printCourseStatistics(String coursetitle, DataContainer datacontainer){
    	String courseStatistics = "Course Title: " + coursetitle + "\n";
    	Course thisCourse = null;
    	//HashMap<String, ArrayList<AssessmentComponent>> courseResult;
    	ArrayList<Student> studentList = new ArrayList<>();
        for(Course course: datacontainer.getCourseList()){
            if (course.GetCourseTitle().equals(coursetitle.toUpperCase())) {
            	thisCourse = course;
            	break;
            	}
        }
        studentList=thisCourse.GetStudentList();
        
        for(Student student:studentList) {
        	
        	
        }
        
        
        
        
        
        System.out.println (courseStatistics);
        }
    }
