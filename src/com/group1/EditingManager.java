package com.group1;


import Exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EditingManager
{
    public static void Register(DataContainer dataContainer){
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

                if(!Validation.CheckCourseExisted(courseTitle,dataContainer)){
                    throw new CourseNotFoundException();
                }
                newCourse=ReadingManager.findCourse(courseTitle,dataContainer);


                if(!ReadingManager.CourseHaveVacancy(courseTitle,dataContainer)){
                    throw new CourseNoVacancyException();}
                if(ReadingManager.CheckStudentRegisteredForCourse(newStudent,newCourse)){
                    throw new StudentAlreadyRegisteredForThisCourseException(newStudent,newCourse);
                }

                EM.RegisterStudentToCourseLecture(newStudent,newCourse);

                if(newCourse.HaveTutorial()==false)
                    break;
                while(true) {
                    System.out.println("Please type the name of a tutorial to be enrolled in: ");

                    int i = 1;
                    while (i <= newCourse.GetTutorialList().size()) {
                        System.out.println(i + ". " + newCourse.GetTutorialList().get(i - 1).GetName() + "\t Vacancy: "+(newCourse.GetTutorialList().get(i - 1).GetTotalVacancy()-newCourse.GetTutorialList().get(i - 1).GetRegisteredStudent().size()));
                        i++;
                    }
                    tutorialName = in.nextLine();

                    if (EM.RegisterStudentToTutorial(newStudent, newCourse, tutorialName))
                        break;
                }


                while(true){
                    if(newCourse.HaveLab()==false)
                        break;

                    System.out.println("Please select a lab to be enrolled in:");
                    int i=1;
                    while(i<=newCourse.GetLabList().size()){
                        System.out.println(i+". "+newCourse.GetLabList().get(i-1).GetName()+"\t Vacancy: "+(newCourse.GetLabList().get(i - 1).GetTotalVacancy()-newCourse.GetLabList().get(i - 1).GetRegisteredStudent().size()));
                        i++;
                    }
                    labName = in.nextLine();

                    if(EM.RegisterStudentToLab(newStudent,newCourse,labName))
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

    public static void RegisterStudentToCourseLecture(Student thisStudent, Course course)
    {
        course.GetStudentList().add(thisStudent);

        HashMap<String, ArrayList<AssessmentComponent>> studentCourseList = thisStudent.GetCourseAndResult();
        ArrayList<AssessmentComponent> result = new ArrayList<>();
        studentCourseList.put(course.getCourseTitle(), result);

        System.out.println("Student " + thisStudent.getMatricNumber() + " " + thisStudent.GetStudentName() + " has been registered to Course " + course.getCourseTitle());
    }

    public static boolean RegisterStudentToTutorial(Student student, Course course, String tutorialName)
    {

        ArrayList<Tutorial> tutorialList = course.GetTutorialList();
        Tutorial thisTutorial = null;
        boolean found = false;

        try
        {
            for (Tutorial tutorial : tutorialList)
            {
                if (tutorialName.equals(tutorial.GetName()))
                {
                    thisTutorial = tutorial;
                    found = true;
                }
            }
            if (found == false) throw new TutorialGroupNonExistentException();

            if (!thisTutorial.HaveVacancy())
                throw new TutorialOrLabNoVacancyException();
            thisTutorial.GetRegisteredStudent().add(student);

            System.out.println("Student " + student.getMatricNumber() + " " + student.GetStudentName() + " has been registered to " + tutorialName + "  bof course " + course.getCourseTitle());

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

    public static boolean RegisterStudentToLab(Student student, Course course, String labName)
    {

        ArrayList<Lab> labList = course.GetLabList();
        Lab thisLab = null;
        boolean found = false;

        try
        {
            for (Lab lab : labList)
            {
                if (labName.equals(lab.GetName()))
                {
                    thisLab = lab;
                    found = true;
                }
            }

            if (found == false)
                throw new LabGroupNonExistentException();

            if (!thisLab.HaveVacancy())
                throw new TutorialOrLabNoVacancyException();
            thisLab.GetRegisteredStudent().add(student);

            System.out.println("Student " + student.getMatricNumber() + " " + student.GetStudentName() + " has been registered to " + labName + " of course " + course.getCourseTitle());
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


    public static void AddCourseComponent(String courtsetitle, DataContainer container)
    {

        Course newcourse6 = null;
        for (Course course : container.getCourseList())
        {
            if (course.getCourseTitle().equals(courtsetitle))
                newcourse6 = course;
        }

        if (!newcourse6.GetComponents().isEmpty())
        {
            System.out.println("This course's component weightage has already been assigned.");
            return;
        }

        String[] component = {"Exam", "Coursework"};

        while (true)
        {
            for (String compname : component)
            {
                newcourse6.assignComponentWeightage(newcourse6.GetComponents(), compname);
            }
            float weightagesum = 0;
            for (AssessmentComponent componentnew : newcourse6.GetComponents())
            {
                weightagesum += componentnew.getWeightage();
            }
            System.out.println(weightagesum);
            if (Validation.ValidateWeightageSum(weightagesum))
            {
                break;
            } else
            {
                System.out.println("The total weightage you have entered is not valid. It should sum up to 1\n");
                newcourse6.GetComponents().clear();
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
                if (Validation.ValidateNumberInput(temp))
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
                if (Validation.ValidateNumberInput(temp) && Integer.parseInt(temp) > 1)
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
                    newcourse6.assignComponentWeightage(newcourse6.GetSubComponents(), subcomname);
                }

                for (AssessmentComponent componentnew : newcourse6.GetSubComponents())
                {

                    subweightagesum += componentnew.getWeightage();
                }
                if (Validation.ValidateWeightageSum(subweightagesum))
                {
                    break;
                } else
                {
                    System.out.println("The total weightage you have entered is not valid. It should sum up to 1\n");
                    newcourse6.GetSubComponents().clear();
                    System.out.println(subweightagesum);
                }
            }
            break;


        }
        System.out.println("Component weightage have been successfully entered.");


    }

    public static void AssignExamResults(String matricnumber, String coursetitle, DataContainer container)
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
            if (!Validation.CheckWhetherStudentRegisteredForACourse(student, coursetitle))
            {
                throw new StudentNotRegisteredForTheCourse();
            } else if (!Validation.CheckWhetherHasAssessmentWeightage(course))
            {
                throw new CourseNoExamComponentException();
            } else if (Validation.CheckStudentResultsRecord(student, coursetitle) != 0)
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
            AssessmentComponent newcomponent = new AssessmentComponent(course.GetComponents().get(0));

            System.out.println("Please enter the student's result for " + newcomponent.getAssessmentType() + ", you should enter a mark between 0-100.");
            String mark = scanner.nextLine();

            if (Validation.ValidateFloatInput(mark) && Float.parseFloat(mark) >= 0 && Float.parseFloat(mark) <= 100)
            {
                newcomponent.setResult(Float.parseFloat(mark));
                student.GetCourseAndResult().get(coursetitle).add(newcomponent);
                break;

            } else
            {
                System.out.println("MarkNotValidException: The mark you entered for the component is not valid. Please check format. You are going to restart mark entering");
                student.ClearHashmapValue(coursetitle);
            }



        }

        System.out.println("You have successfully assigned the result for this student. Press any key to continue.");
        scanner.next();


    }


    public static void AssignCourseworkResults(String matricnumber, String coursetitle, DataContainer container)
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
            if (!Validation.CheckWhetherStudentRegisteredForACourse(student, coursetitle))
            {
                throw new StudentNotRegisteredForTheCourse();
            } else if (!Validation.CheckWhetherHasAssessmentWeightage(course))
            {
                throw new CourseNoExamComponentException();
            } else if (Validation.CheckStudentResultsRecord(student, coursetitle) > 1)
            {
                throw new StudentResultAlreadyExistsException();
            } else if (Validation.CheckStudentResultsRecord(student, coursetitle) < 1)
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

        while ( z == 1)
        {
            if (course.GetSubComponents().isEmpty())
            {
                y = 0 ;
                AssessmentComponent newcomponent = new AssessmentComponent(course.GetComponents().get(1));
                System.out.println("Please enter the student's result for " + newcomponent.getAssessmentType() + ", you should enter a mark between 0-100.");
                String mark = scanner.nextLine();
                if (Validation.ValidateFloatInput(mark) && Float.parseFloat(mark) >= 0 && Float.parseFloat(mark) <= 100)
                {

                    courseworkresult =Float.parseFloat(mark);// newcomponent.getWeightage() *
                    newcomponent.setResult(courseworkresult);
                    student.GetCourseAndResult().get(coursetitle).add(newcomponent);
                    z = 0;
                } else
                {
                    System.out.println("MarkNotValidException: The mark you entered for the component is not valid. Please check format. You are going to restart mark entering");
                    student.ClearHashmapValue(coursetitle);
                    courseworkresult = 0;
                    z = 1;
                }
            }
            else
            {
                y = 1;
                AssessmentComponent newcomponent = new AssessmentComponent(course.GetComponents().get(1));
                student.GetCourseAndResult().get(coursetitle).add(newcomponent);
                z = 0;
            }
        }

        while (y == 1)
        {
            for (AssessmentComponent component : course.GetSubComponents())
            {
                AssessmentComponent newcomponent = new AssessmentComponent(component);

                System.out.println("Please enter the student's result for " + newcomponent.getAssessmentType() + ", you should enter a mark between 0-100.");
                String mark = scanner.nextLine();
                if (Validation.ValidateFloatInput(mark) && Float.parseFloat(mark) >= 0 && Float.parseFloat(mark) <= 100)
                {
                    newcomponent.setResult(Float.parseFloat(mark));
                    courseworkresult += newcomponent.getWeightage() * newcomponent.getResult();
                    student.GetCourseAndResult().get(coursetitle).add(newcomponent);
                    y = 0;
                } else
                {
                    System.out.println("MarkNotValidException: The mark you entered for the component is not valid. Please check format. You are going to restart mark entering");
                    student.ClearHashmapValue(coursetitle);
                    courseworkresult = 0;

                    y = 1;
                    break;
                }

            }
        }



        student.GetCourseAndResult().get(coursetitle).get(1).setResult(courseworkresult);

        System.out.println("You have successfully assigned the course work result for this student. Press any key to continue.");
        scanner.next();


    }

}
