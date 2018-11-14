package SCRAME_grp1;

/**
 * A serializable class representing a lab group attached to a certain course
 */

public class Lab extends Session implements java.io.Serializable{
    public Lab(String sessionName, int totalVacancy){
        super(sessionName, totalVacancy);
    }
    public Lab(String sessionName) {super(sessionName);}
}
