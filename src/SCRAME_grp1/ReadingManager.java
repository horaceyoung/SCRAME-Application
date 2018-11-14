package SCRAME_grp1;

import Exceptions.CourseNotFoundException;
import Exceptions.StudentResultNotExistentException;
import Exceptions.TutorialLabNotAvailableException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A controller class in charge of reading data from the dataContainer and prepare the data for other classes' use
 */
public class ReadingManager
{
    /**
     * read the information of professors from an external txt file
     * @param dataContainer the read professor object will be stored in the dataContainer
     */
    public static void readProefessors(DataContainer dataContainer){
        String line;
        String[] Prof;
        try{
            BufferedReader profIn = new BufferedReader(new FileReader("data/Professors.txt"));
            while((line = profIn.readLine())!=null){
                Prof = line.split("\t");
                dataContainer.professors.add(new Professor(Prof[0], Prof[1]));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    /**
     * Print the list of students who registered a designated course
     * @param course the course code the user wish to inquire
     */

    public static void printStudentList(Course course) {
        if(course.getStudentList().isEmpty()){
            System.out.println(course.getCourseTitle() + " currently has no student enrolled in.");
            return;
        }


        System.out.println("Students registered in " + course.getCourseTitle() + " : ");
        ArrayList<Student> studentList = course.getStudentList();
        for(Student student : studentList){
            System.out.println(student.getMatricNumber() + student.getName());
        }
    }


    /**
     * Print the list of students who registered a designated tutorial group
     * @param tutorial the tutorial the user wish to inquire
     */
    public static void printStudentList(Tutorial tutorial) {
        if(tutorial.getRegisteredStudent().isEmpty()){
            System.out.println(tutorial.getName()+ " currently has no student enrolled in.");
            return;
        }
        System.out.println("Students registered in tutorial group " + tutorial.getName() + " : ");
        ArrayList<Student> studentList = tutorial.getRegisteredStudent();
        for(Student student : studentList){
            System.out.println(student.getMatricNumber() + student.getName());
        }
    }

    /**
     * Print the list of students who registered a designated lab group
     * @param lab the lab the user wish to inquire
     */
    public static void printStudentList(Lab lab) {
        if(lab.getRegisteredStudent().isEmpty()){
            System.out.println(lab.getName()+ " currently has no student enrolled in.");
            return;
        }


        System.out.println("Students registered in lab group" + lab.getName() + " : ");
        ArrayList<Student> studentList = lab.getRegisteredStudent();
        for(Student student : studentList){
            System.out.println(student.getMatricNumber() + student.getName());
        }
    }

    /**
     * Check if a student has registered a certain course
     * @param thisStudent The student which the user wish to inquire
     * @param course The course which the user wish to inquire
     * @return if the student has registered this course return true, otherwise return false
     */
    public static boolean checkStudentRegisteredForCourse(Student thisStudent, Course course){

        ArrayList<Student> studentList = course.getStudentList();

        for(Student student:studentList){
            if(thisStudent.getMatricNumber().equals(student.getMatricNumber()))
                return true;

        }
        return false;
    }

    /**
     *Print the vacancy of a designated tutorial
     * @param courseTitle the course title that the tutorial belongs to
     * @param dataContainer the dataContainer object that contains all the data
     */
    public static void printTutorialVacancy(String courseTitle, DataContainer dataContainer)  throws TutorialLabNotAvailableException{
        ArrayList<Tutorial> tutorialList = new ArrayList<>();
        ArrayList<Course> courseList = dataContainer.getCourseList();
        try {
            for (Course course : courseList) {
                if (courseTitle.equals(course.getCourseTitle())) {
                    if (course.haveTutorial() == false)
                        throw new TutorialLabNotAvailableException();
                    else tutorialList = course.getTutorialList();

                }
            }



            System.out.println("The Vacancy of Tutorial Groups of " + courseTitle + " is as following: ");

            for (Tutorial tutorial : tutorialList) {
                System.out.println(tutorial.getName() + " " + tutorial.getRegisteredStudent().size() + "/" + tutorial.getTotalVacancy());


            }
        }
        catch (TutorialLabNotAvailableException e){System.out.println(e.getMessage());}
    }

    /**
     * Search a certain course from the database
     * @param courseTitle The course title that the user wish to inquire
     * @param dataContainer The dataContainer object that contains all the data
     * @return a course if the course title exists, otherwise return null
     */
    public static Course findCourse(String courseTitle, DataContainer dataContainer){
        Course newCourse = null;
        ArrayList<Course> courseList = dataContainer.getCourseList();
        for(Course course:courseList){
            if(courseTitle.equals(course.getCourseTitle())){
                newCourse=course;
                break;
            }
        }
        return newCourse;
    }

    /**
     * Print the vacancy of a certain lab
     * @param courseTitle The course title that the lab belongs to
     * @param dataContainer The dataContainer object that contains all the data
     */
    public static void printLabVacancy(String courseTitle, DataContainer dataContainer) {
        ArrayList<Lab> labList = new ArrayList<>();
        ArrayList<Course> courseList = dataContainer.getCourseList();
        try {
            for (Course course : courseList) {
                if (courseTitle.equals(course.getCourseTitle())) {
                    if (course.haveLab() == false)
                        throw new TutorialLabNotAvailableException();
                    else labList= course.getLabList();
                break;
                }
            }

            System.out.println("The Vacancy of Lab Groups of " + courseTitle + " is as following: ");

            for (Lab lab : labList) {
                System.out.println(lab.getName() + " " + lab.getRegisteredStudent().size() + "/" + lab.getTotalVacancy());


            }
        }
        catch (TutorialLabNotAvailableException e){System.out.println(e.getMessage());}
    }

    /**
     * Check if the course still has vacancy, aka, both tutorials and lab groups have vacancies if the course has either tutorial or lab sessions, or the course only has lecture
     * @param courseTitle The title of the course which the user wish to inquire
     * @param dataContainer The dataContainer object that contains all the data
     * @return
     */
    public static boolean courseHaveVacancy(String courseTitle, DataContainer dataContainer){
        boolean haveVacancy = false;
        ArrayList<Course> courseList = dataContainer.getCourseList();
        Course thisCourse=null;
        for (Course course : courseList) {
            if (courseTitle.equals(course.getCourseTitle())) {
                thisCourse=course;
                break;
            }
        }
        if(!thisCourse.haveTutorial()) return true;

        ArrayList<Tutorial> tutorialList = thisCourse.getTutorialList();
        for(Tutorial tutorial:tutorialList){
            if(tutorial.haveVacancy())
                haveVacancy = true;
        }

        return haveVacancy;

    }

    /**
     * Print the transcript of a student
     * @param studentMatricTranscript The matriculation number of the student the user wish to inquire
     * @param dataContainer The dataContainer object that contains all the data
     */
    public static void printTranscript(String studentMatricTranscript, DataContainer dataContainer){

        String transcriptOutcome = "Matric No: " + studentMatricTranscript + "\n";
        HashMap<String, ArrayList<AssessmentComponent>> courseAndResult;
        HashMap<String, ArrayList<AssessmentComponent>> currentCourse;
        int count = 2;
        float overallMark = 0;
        //read Results file to get results
        for(Student student : dataContainer.getStudentsList()) {
            if (student.getMatricNumber().toUpperCase().equals(studentMatricTranscript.toUpperCase())) {
                transcriptOutcome += "Student Name: " + student.getName() + "\n";

                courseAndResult = student.getCourseAndResult();
                for (String key : courseAndResult.keySet()) {
                    transcriptOutcome += key + "\n" + "Overall Mark: " + "\n";
                    ArrayList<AssessmentComponent> components = courseAndResult.get(key);
                    for (AssessmentComponent component : components) {
                        if (count > 0) {
                            overallMark += component.getWeightage() * component.getResult();
                            count--;
                        }
                        transcriptOutcome += "\t" + component.getAssessmentType() + " " + component.getWeightage() + " "
                                + component.getResult() + "\n";
                    }
                }

            }
        }transcriptOutcome += "\tOverall (Exam + Coursework): " + overallMark;
        System.out.println(transcriptOutcome);
    }

    /**
     * Print the course's statistics, including the overall average grades, grades for individual components, and grades percentages
     * @param courseTitle The course title which the user wish to inquire
     * @param datacontainer The dataContainer object that contains all the data
     */
    public static void printCourseStatistics(String courseTitle, DataContainer datacontainer){
        Course thisCourse = null;
        ArrayList<Student> studentList = new ArrayList<>();
        for(Course course: datacontainer.getCourseList()){
            if (course.getCourseTitle().equals(courseTitle.toUpperCase())) {
                thisCourse = course;
                break;
            }
        }
        studentList=thisCourse.getStudentList();
        float examResult=0;
        float courseWorkResult=0;
        int studentNumCounter = 0;
        int examAGrade = 0;
        int examBGrade = 0;
        int examCGrade=0;
        int examDGrade=0;
        int examFGrade=0;
        int courseworkAGrade=0;
        int courseworkBGrade=0;
        int courseworkCGrade=0;
        int courseworkDGrade=0;
        int courseworkFGrade=0;
        int overallResultA =0;
        int overallResultB =0;
        int overallResultC =0;
        int overallResultD =0;
        int overallResultF =0;

        HashMap<String,ArrayList<AssessmentComponent>> resultList = new HashMap<>();
        ArrayList<AssessmentComponent> result = new ArrayList<>();

        try {
            for (Student student : studentList) {

                resultList = student.getCourseAndResult();
                result = resultList.get(courseTitle);
                if (result.size()<1)
                    throw new StudentResultNotExistentException(student, thisCourse);
                examResult += result.get(0).getResult();
                courseWorkResult += result.get(1).getResult();
                studentNumCounter++;
            }

            for (Student student : studentList) {

                resultList = student.getCourseAndResult();
                result = resultList.get(courseTitle);
                if (result.size()<1)
                    throw new StudentResultNotExistentException(student, thisCourse);

                if (result.get(0).getResult() >= 75){
                    examAGrade++;
                }
                else if (result.get(0).getResult() >= 65 && result.get(0).getResult() <= 74){
                    examBGrade++;
                }
                else if (result.get(0).getResult() >= 55 && result.get(0).getResult() <= 64){
                    examCGrade++;
                }
                else if (result.get(0).getResult() >= 45 && result.get(0).getResult() <= 54){
                    examDGrade++;
                }
                else if (result.get(0).getResult() < 45){
                    examFGrade++;
                }
            }

            for (Student student : studentList) {

                resultList = student.getCourseAndResult();
                result = resultList.get(courseTitle);
                if (result.size()<1)
                    throw new StudentResultNotExistentException(student, thisCourse);

                if (result.get(1).getResult() >= 75){
                    courseworkAGrade++;
                }
                else if (result.get(1).getResult() >= 65 && result.get(1).getResult() <= 74){
                    courseworkBGrade++;
                }
                else if (result.get(1).getResult() >= 55 && result.get(1).getResult() <= 64){
                    courseworkCGrade++;
                }
                else if (result.get(1).getResult() >= 45 && result.get(1).getResult() <= 54){
                    courseworkDGrade++;
                }
                else if (result.get(1).getResult() < 45){
                    courseworkFGrade++;
                }
            }

            for (Student student : studentList) {

                resultList = student.getCourseAndResult();
                result = resultList.get(courseTitle);
                if (result.size() < 1)
                    throw new StudentResultNotExistentException(student, thisCourse);

                float overallResult = 0;
                float personalexamResult = result.get(0).getResult();
                float personalCourseworkResult = result.get(1).getResult();
                overallResult =personalexamResult * result.get(0).getWeightage()+ personalCourseworkResult * result.get(1).getWeightage();

                if (overallResult >= 75) {
                    overallResultA++;
                } else if (overallResult >= 65 && overallResult <= 74) {
                    overallResultB++;
                } else if (overallResult >= 55 && overallResult <= 64) {
                    overallResultC++;
                } else if (overallResult >= 45 && overallResult <= 54) {
                    overallResultD++;
                } else if (overallResult < 45) {
                    overallResultF++;
                }
            }



        }catch (StudentResultNotExistentException e){System.out.println(e.getMessage());}

        int studentSize = studentList.size();
        float examAve = examResult/studentSize;
        float courseWorkAve = courseWorkResult/studentSize;
        float overallAve = examAve * result.get(0).getWeightage()+courseWorkAve*result.get(1).getWeightage();

        float studentNumCounterFloat = studentNumCounter;
        //A
        float examAGradeFloat = examAGrade;
        float examAPercentage = examAGradeFloat/studentNumCounterFloat;

        float courseworkAGradeFloat = courseworkAGrade;
        float courseworkAGradePercentage = courseworkAGradeFloat / studentNumCounterFloat;

        float overallResultAFloat = overallResultA;
        float overallResultAFloatPercentage = overallResultAFloat / studentNumCounterFloat;

        //B
        float examBGradeFloat = examBGrade;
        float examBPercentage = examBGradeFloat/studentNumCounterFloat;

        float courseworkBGradeFloat = courseworkBGrade;
        float courseworkBGradePercentage = courseworkBGradeFloat / studentNumCounterFloat;

        float overallResultBFloat = overallResultB;
        float overallResultBFloatPercentage = overallResultBFloat / studentNumCounterFloat;
        //C
        float examCGradeFloat = examCGrade;
        float examCPercentage = examCGradeFloat/studentNumCounterFloat;

        float courseworkCGradeFloat = courseworkCGrade;
        float courseworkCGradePercentage = courseworkCGradeFloat / studentNumCounterFloat;

        float overallResultCFloat = overallResultC;
        float overallResultCFloatPercentage = overallResultCFloat / studentNumCounterFloat;
        //D
        float examDGradeFloat = examDGrade;
        float examDPercentage = examDGradeFloat/studentNumCounterFloat;

        float courseworkDGradeFloat = courseworkDGrade;
        float courseworkDGradePercentage = courseworkDGradeFloat / studentNumCounterFloat;

        float overallResultDFloat = overallResultD;
        float overallResultDFloatPercentage = overallResultDFloat / studentNumCounterFloat;
        //F
        float examFGradeFloat = examFGrade;
        float examFPercentage = examFGradeFloat/studentNumCounterFloat;

        float courseworkFGradeFloat = courseworkFGrade;
        float courseworkFGradePercentage = courseworkFGradeFloat / studentNumCounterFloat;

        float overallResultFFloat = overallResultF;
        float overallResultFFloatPercentage = overallResultFFloat / studentNumCounterFloat;



        System.out.println(
                "number of student:" + studentNumCounter
                        + "\nCourse: " + courseTitle
                        + "\nAverage Exam Mark - "+examAve + "\n"
                        + "A Grade: " + (examAPercentage)*100 + "%\n"
                        + "B Grade: " + (examBPercentage)*100 + "%\n"
                        + "C Grade: " + (examCPercentage)*100 + "%\n"
                        + "D Grade: " + (examDPercentage)*100 + "%\n"
                        + "F Grade: " + (examFPercentage)*100 + "%\n"
                        +"\nAverage Course Work Mark - "+courseWorkAve + "\n"
                        + "A Grade: " + (courseworkAGradePercentage)*100 + "%\n"
                        + "B Grade: " + (courseworkBGradePercentage)*100 + "%\n"
                        + "C Grade: " + (courseworkCGradePercentage)*100 + "%\n"
                        + "D Grade: " + (courseworkDGradePercentage)*100 + "%\n"
                        + "F Grade: " + (courseworkFGradePercentage)*100 + "%\n"
                        +"\nAverage (Exam + Course Work) Mark - "+overallAve + "\n"
                        + "A Grade: " + (overallResultAFloatPercentage)*100 + "%\n"
                        + "B Grade: " + (overallResultBFloatPercentage)*100 + "%\n"
                        + "C Grade: " + (overallResultCFloatPercentage)*100 + "%\n"
                        + "D Grade: " + (overallResultDFloatPercentage)*100 + "%\n"
                        + "F Grade: " + (overallResultFFloatPercentage)*100 + "%\n"
        );


    }

    /**
     * The generic method to print vacancy for a specific type of session
     * @param dataContainer The dataContainer object that contains all the data
     */
    public static void printVacancy(DataContainer dataContainer){
        String studentMatric;
        String courseTitle;
        String tutorialName;
        String labName;
        Student newStudent = null;
        Course newCourse = null;
        while(true){
        Scanner in = new Scanner(System.in);
        System.out.println("Check Session Vacancy: Please input the course title you want to check: ");
        courseTitle = in.nextLine();

            try{
                if(Validation.checkCourseExisted(courseTitle, dataContainer)){
                    System.out.println("Check Session Vacancy: Please select the type of session you wish to check by inputting corresponding integer value:\n 1. Tutorial \n2.Lab \n");
                    String sessionChoice=in.next();
                    if(!sessionChoice.equals("1")&&!sessionChoice.equals("2"))
                    {
                        System.out.println("Please input valid choices.");
                        continue;
                    }
                    switch (sessionChoice){
                        case "1":
                            ReadingManager.printTutorialVacancy(courseTitle, dataContainer);
                            break;
                        case "2":
                            ReadingManager.printLabVacancy(courseTitle, dataContainer);
                            break;
                    }
                    break;
                }
                else {
                    throw new CourseNotFoundException();
                }

            }
            catch (TutorialLabNotAvailableException e){
                System.out.println(e.getMessage());
                continue;
            }
            catch (CourseNotFoundException e){
                System.out.println(e.getMessage());
                continue;
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                continue;
            }
        }
    }
}
