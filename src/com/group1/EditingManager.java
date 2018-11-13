package com.group1;


import Exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EditingManager
{
    public static void register(DataContainer dataContainer){
        String studentMatric;
        String courseTitle;
        String tutorialName;
        String labName;
        Student newStudent = null;
        Course newCourse = null;

        Scanner in = new Scanner(System.in);
        while(true){


            System.out.println("Register course: Please input the matric number of the student to register: (Enter 0 to exit)");
            EditingManager EM = new EditingManager();
            studentMatric = in.nextLine();
            if(studentMatric.equals("0"))
                break;
            try{
                if(!Validation.studentExists(studentMatric,dataContainer))
                    throw new StudentNotExistException();
                else{
                    ArrayList<Student> studentList = dataContainer.getStudentsList();
                    for(Student student:studentList){
                        if(studentMatric.equals(student.getMatricNumber()))
                            newStudent = student;
                    }

                }

                System.out.println("Register course: Please input the course title you want to register with: (Enter 0 to exit)");
                courseTitle = in.nextLine();
                if(courseTitle.equals("0"))
                    break;

                if(!Validation.checkCourseExisted(courseTitle,dataContainer)){
                    throw new CourseNotFoundException();
                }
                newCourse=ReadingManager.findCourse(courseTitle,dataContainer);


                if(!ReadingManager.courseHaveVacancy(courseTitle,dataContainer)){
                    throw new CourseNoVacancyException();}
                if(ReadingManager.checkStudentRegisteredForCourse(newStudent,newCourse)){
                    throw new StudentAlreadyRegisteredForThisCourseException(newStudent,newCourse);
                }

                EM.registerStudentToCourseLecture(newStudent,newCourse);

                if(newCourse.haveTutorial()==false)
                    break;
                while(true) {
                    System.out.println("Please type the name of a tutorial to be enrolled in: ");

                    int i = 1;
                    while (i <= newCourse.getTutorialList().size()) {
                        System.out.println(i + ". " + newCourse.getTutorialList().get(i - 1).getName() + "\t Vacancy: "+(newCourse.getTutorialList().get(i - 1).getTotalVacancy()-newCourse.getTutorialList().get(i - 1).getRegisteredStudent().size()));
                        i++;
                    }
                    tutorialName = in.nextLine();

                    if (EM.registerStudentToTutorial(newStudent, newCourse, tutorialName))
                        break;
                }


                while(true){
                    if(newCourse.haveLab()==false)
                        break;

                    System.out.println("Please select a lab to be enrolled in:");
                    int i=1;
                    while(i<=newCourse.getLabList().size()){
                        System.out.println(i+". "+newCourse.getLabList().get(i-1).getName()+"\t Vacancy: "+(newCourse.getLabList().get(i - 1).getTotalVacancy()-newCourse.getLabList().get(i - 1).getRegisteredStudent().size()));
                        i++;
                    }
                    labName = in.nextLine();

                    if(EM.registerStudentToLab(newStudent,newCourse,labName))
                        break;
                }
                break;
            }
            catch (CourseNoVacancyException e){System.out.println(e.getMessage());}
            catch (StudentAlreadyRegisteredForThisCourseException e){System.out.print( e.getMessage());}
            catch (CourseNotFoundException e){System.out.println(e.getMessage());}
            catch (StudentNotExistException e){System.out.println(e.getMessage());}
        }
    }

    public static void registerStudentToCourseLecture(Student thisStudent, Course course)
    {
        course.getStudentList().add(thisStudent);

        HashMap<String, ArrayList<AssessmentComponent>> studentCourseList = thisStudent.getCourseAndResult();
        ArrayList<AssessmentComponent> result = new ArrayList<>();
        studentCourseList.put(course.getCourseTitle(), result);

        System.out.println("Student " + thisStudent.getMatricNumber() + " " + thisStudent.getName() + " has been registered to Course " + course.getCourseTitle());
    }

    public static boolean registerStudentToTutorial(Student student, Course course, String tutorialName)
    {

        ArrayList<Tutorial> tutorialList = course.getTutorialList();
        Tutorial thisTutorial = null;
        boolean found = false;

        try
        {
            for (Tutorial tutorial : tutorialList)
            {
                if (tutorialName.equals(tutorial.getName()))
                {
                    thisTutorial = tutorial;
                    found = true;
                }
            }
            if (found == false) throw new TutorialGroupNonExistentException();

            if (!thisTutorial.haveVacancy())
                throw new TutorialOrLabNoVacancyException();
            thisTutorial.getRegisteredStudent().add(student);

            System.out.println("Student " + student.getMatricNumber() + " " + student.getName() + " has been registered to " + tutorialName + "  bof course " + course.getCourseTitle());

            return true;
        } catch (TutorialGroupNonExistentException e)
        {
            System.out.println(e.getMessage());
            return false;
        } catch (TutorialOrLabNoVacancyException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean registerStudentToLab(Student student, Course course, String labName)
    {

        ArrayList<Lab> labList = course.getLabList();
        Lab thisLab = null;
        boolean found = false;

        try
        {
            for (Lab lab : labList)
            {
                if (labName.equals(lab.getName()))
                {
                    thisLab = lab;
                    found = true;
                }
            }

            if (found == false)
                throw new LabGroupNonExistentException();

            if (!thisLab.haveVacancy())
                throw new TutorialOrLabNoVacancyException();
            thisLab.getRegisteredStudent().add(student);

            System.out.println("Student " + student.getMatricNumber() + " " + student.getName() + " has been registered to " + labName + " of course " + course.getCourseTitle());
            return true;
        } catch (LabGroupNonExistentException e)
        {
            System.out.println(e.getMessage());
            return false;
        } catch (TutorialOrLabNoVacancyException e)
        {
            System.out.println(e.getMessage());
            return false;
        }

    }


    public static void addCourseComponent(String courtsetitle, DataContainer container)
    {

        Course newcourse6 = null;
        for (Course course : container.getCourseList())
        {
            if (course.getCourseTitle().equals(courtsetitle))
                newcourse6 = course;
        }

        if (!newcourse6.getComponents().isEmpty())
        {
            System.out.println("This course's component weightage has already been assigned.");
            return;
        }

        String[] component = {"Exam", "Coursework"};

        while (true)
        {
            for (String compname : component)
            {
                newcourse6.assignComponentWeightage(newcourse6.getComponents(), compname);
            }
            float weightagesum = 0;
            for (AssessmentComponent componentnew : newcourse6.getComponents())
            {
                weightagesum += componentnew.getWeightage();
            }
            System.out.println(weightagesum);
            if (Validation.validateWeightageSum(weightagesum))
            {
                break;
            } else
            {
                System.out.println("The total weightage you have entered is not valid. It should sum up to 1\n");
                newcourse6.getComponents().clear();
                weightagesum = 0;
            }
        }


        while (true)
        {
            String temp;
            while (true)
            {
                System.out.println("Do we have sub-component for coursework? Yes: Enter 1  No: Enter 0");
                Scanner ynscanner = new Scanner(System.in);
                temp = ynscanner.nextLine();
                if (Validation.validateNumberInput(temp))
                {
                    break;

                } else
                {
                    System.out.println("Input not valid, please enter 0 or 1\n");
                }

            }

            if (Integer.parseInt(temp) == 0)
            {
                break;
            }

            while (true)
            {
                System.out.println("How many sub-components do u have?");
                Scanner numscanner = new Scanner(System.in);
                temp = numscanner.nextLine();
                if (Validation.validateNumberInput(temp) && Integer.parseInt(temp) > 1)
                {
                    break;

                } else
                {
                    System.out.println("Input not valid, please enter number greater than 1.\n");
                }
            }
            int subcomnum = Integer.parseInt(temp);
            String[] subcoponent = new String[subcomnum];

            for (int x = 1; x <= subcomnum; x++)
            {
                System.out.println("Please enter sub-component " + x + " name");
                Scanner subcomponentscanner = new Scanner(System.in);
                subcoponent[x - 1] = subcomponentscanner.nextLine();


            }
            while (true)
            {

                float subweightagesum = 0;
                for (String subcomname : subcoponent)
                {
                    newcourse6.assignComponentWeightage(newcourse6.getSubComponents(), subcomname);
                }

                for (AssessmentComponent componentnew : newcourse6.getSubComponents())
                {

                    subweightagesum += componentnew.getWeightage();
                }
                if (Validation.validateWeightageSum(subweightagesum))
                {
                    break;
                } else
                {
                    System.out.println("The total weightage you have entered is not valid. It should sum up to 1\n");
                    newcourse6.getSubComponents().clear();
                    System.out.println(subweightagesum);
                }
            }
            break;


        }
        System.out.println("Component weightage have been successfully entered.");


    }

    public static void assignExamResults(String matricnumber, String coursetitle, DataContainer container)
    {
        Scanner scanner = new Scanner(System.in);
        Student student = null;
        Course course = null;

        for (Course mycourse : container.getCourseList())
        {
            if (mycourse.getCourseTitle().equals(coursetitle))
            {
                course = mycourse;
            }
        }
        for (Student mystudent : container.getStudentsList())
        {
            if (mystudent.getMatricNumber().equals(matricnumber))
            {
                student = mystudent;
            }
        }
        try
        {
            if (!Validation.checkWhetherStudentRegisteredForACourse(student, coursetitle))
            {
                throw new StudentNotRegisteredForTheCourse();
            } else if (!Validation.checkWhetherHasAssessmentWeightage(course))
            {
                throw new CourseNoExamComponentException();
            } else if (Validation.checkStudentResultsRecord(student, coursetitle) != 0)
            {
                throw new StudentResultAlreadyExistsException();
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Press any key to continue.");
            scanner.next();
            return;
        }


        while (true)
        {
            AssessmentComponent newcomponent = new AssessmentComponent(course.getComponents().get(0));

            System.out.println("Please enter the student's result for " + newcomponent.getAssessmentType() + ", you should enter a mark between 0-100.");
            String mark = scanner.nextLine();

            if (Validation.validateFloatInput(mark) && Float.parseFloat(mark) >= 0 && Float.parseFloat(mark) <= 100)
            {
                newcomponent.setResult(Float.parseFloat(mark));
                student.getCourseAndResult().get(coursetitle).add(0,newcomponent);
                break;

            } else
            {
                System.out.println("MarkNotValidException: The mark you entered for the component is not valid. Please check format. You are going to restart mark entering");

            }



        }

        System.out.println("You have successfully assigned the result for this student. Press any key to continue.");
        scanner.next();


    }


    public static void assignCourseworkResults(String matricnumber, String coursetitle, DataContainer container)
    {
        Scanner scanner = new Scanner(System.in);
        Student student = null;
        Course course = null;
        ArrayList<AssessmentComponent> templist = new ArrayList<>();

        for (Course mycourse : container.getCourseList())
        {
            if (mycourse.getCourseTitle().equals(coursetitle))
            {
                course = mycourse;
            }
        }
        for (Student mystudent : container.getStudentsList())
        {
            if (mystudent.getMatricNumber().equals(matricnumber))
            {
                student = mystudent;
            }
        }
        try
        {
            if (!Validation.checkWhetherStudentRegisteredForACourse(student, coursetitle))
            {
                throw new StudentNotRegisteredForTheCourse();
            } else if (!Validation.checkWhetherHasAssessmentWeightage(course))
            {
                throw new CourseNoExamComponentException();
            } else if (Validation.checkStudentResultsRecord(student, coursetitle) > 1)
            {
                throw new StudentResultAlreadyExistsException();
            } else if (Validation.checkStudentResultsRecord(student, coursetitle) < 1)
            {
                throw new StudentResultNotExistentException(student, course);
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Press any key to continue.");
            scanner.next();
            return;
        }

        int y = 1;
        int z = 1;
        float courseworkresult = 0;
        AssessmentComponent coursework = new AssessmentComponent(0,"coursework");
        while ( z == 1)
        {
            if (course.getSubComponents().isEmpty())
            {
                y = 0 ;
                AssessmentComponent newcomponent = new AssessmentComponent(course.getComponents().get(1));
                System.out.println("Please enter the student's result for " + newcomponent.getAssessmentType() + ", you should enter a mark between 0-100.");
                String mark = scanner.nextLine();
                if (Validation.validateFloatInput(mark) && Float.parseFloat(mark) >= 0 && Float.parseFloat(mark) <= 100)
                {

                    courseworkresult =Float.parseFloat(mark);// newcomponent.GetWeightage() *
                    newcomponent.setResult(courseworkresult);
                    templist.add(newcomponent);
                    coursework = newcomponent;
                    //student.GetCourseAndResult().get(coursetitle).add(newcomponent);
                    z = 0;
                } else
                {
                    System.out.println("MarkNotValidException: The mark you entered for the component is not valid. Please check format. You are going to restart mark entering");
                    templist.clear();
                    //student.ClearHashmapValue(coursetitle);
                    courseworkresult = 0;
                    z = 1;
                }
            }
            else
            {
                y = 1;
                AssessmentComponent newcomponent = new AssessmentComponent(course.getComponents().get(1));
                templist.add(newcomponent);
                coursework = newcomponent;
                //student.GetCourseAndResult().get(coursetitle).add(newcomponent);
                z = 0;
            }
        }

        while (y == 1)
        {
            for (AssessmentComponent component : course.getSubComponents())
            {
                AssessmentComponent newcomponent = new AssessmentComponent(component);

                System.out.println("Please enter the student's result for " + newcomponent.getAssessmentType() + ", you should enter a mark between 0-100.");
                String mark = scanner.nextLine();
                if (Validation.validateFloatInput(mark) && Float.parseFloat(mark) >= 0 && Float.parseFloat(mark) <= 100)
                {
                    newcomponent.setResult(Float.parseFloat(mark));
                    courseworkresult += newcomponent.getWeightage() * newcomponent.getResult();
                    templist.add(newcomponent);
                    //student.GetCourseAndResult().get(coursetitle).add(newcomponent);
                    y = 0;
                } else
                {
                    System.out.println("MarkNotValidException: The mark you entered for the component is not valid. Please check format. You are going to restart mark entering");
                    //student.ClearHashmapValue(coursetitle);
                    templist.clear();
                    templist.add(coursework);
                    courseworkresult = 0;

                    y = 1;
                    break;
                }

            }
        }


        templist.get(0).setResult(courseworkresult);
        //student.GetCourseAndResult().get(coursetitle).get(1).setResult(courseworkresult);
        for (AssessmentComponent item : templist)
        {
            student.getCourseAndResult().get(coursetitle).add(item);
        }
        System.out.println("You have successfully assigned the course work result for this student. Press any key to continue.");
        scanner.next();


    }

}
