package com.group1;


import Exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EditingManager
{
    public void RegisterStudentToCourseLecture(Student thisStudent, Course course) {
        course.GetStudentList().add(thisStudent);

        HashMap<String,ArrayList<AssessmentComponent>> studentCourseList = thisStudent.GetCourseAndResult();
        ArrayList<AssessmentComponent> result = new ArrayList<>();
        studentCourseList.put(course.getCourseTitle(),result);

        System.out.println("Student "+thisStudent.getMatricNumber()+" "+thisStudent.GetStudentName()+" has been registered to Course "+course.getCourseTitle());
    }

    public boolean RegisterStudentToTutorial(Student student,Course course, String tutorialName){

        ArrayList<Tutorial> tutorialList = course.GetTutorialList();
        Tutorial thisTutorial=null;
        boolean found=false;

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

         System.out.println("Student "+student.getMatricNumber()+" "+student.GetStudentName()+" has been registered to "+ tutorialName+ "  bof course "+course.getCourseTitle());

         return true;
        }
        catch (TutorialGroupNonExistentException e){
            System.out.println(e.getMessage());
            return false;
        }
        catch (TutorialOrLabNoVacancyException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean RegisterStudentToLab(Student student, Course course, String labName){

        ArrayList<Lab> labList = course.GetLabList();
        Lab thisLab=null;
        boolean found = false;

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

            System.out.println("Student "+student.getMatricNumber()+" "+student.GetStudentName()+" has been registered to "+ labName+ " of course "+course.getCourseTitle());
            return true;
        }
        catch (LabGroupNonExistentException e){
            System.out.println(e.getMessage());
            return false;
        }
        catch (TutorialOrLabNoVacancyException e){
            System.out.println(e.getMessage());
            return false;
        }

    }


    public static void AddCourseComponent(String courtsetitle, DataContainer container) {

        Course newcourse6 = null;
        for ( Course course:container.getCourseList()){
            if (course.getCourseTitle().equals(courtsetitle))
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
                newcourse6.assignComponentWeightage(newcourse6.GetComponents(), compname);
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
                weightagesum = 0;
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

                float subweightagesum = 0;
                for (String subcomname : subcoponent) {
                    newcourse6.assignComponentWeightage(newcourse6.GetSubComponents(), subcomname);
                }
                
                for (AssessmentComponent componentnew : newcourse6.GetSubComponents()) {

                    subweightagesum += componentnew.getWeightage();
                }
                if (Validation.ValidateWeightageSum(subweightagesum)) {
                    break;
                } else {
                    System.out.println("The total weightage you have entered is not valid. It should sum up to 1\n");
                    newcourse6.ClearSubComponentWeightage();
                    System.out.println(subweightagesum);
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
            if (mycourse.getCourseTitle().equals(coursetitle))
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

                    if (Validation.ValidateFloatInput(mark) && Float.parseFloat(mark) >= 0 && Float.parseFloat(mark) <= 100)
                    {
                        newcomponent.setResult(Float.parseFloat(mark));


                        try
                        {
                            student.GetCourseAndResult().get(coursetitle).add(newcomponent);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }



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
    }
