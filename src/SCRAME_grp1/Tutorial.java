package SCRAME_grp1;

/**
 * A serializable class representing a tutorial group attached to a certain course
 */
public class Tutorial extends Session implements java.io.Serializable{
    /**
     * The constructor of the tutorial
     * @param sessionName The name of the tutorial
     */
    public Tutorial(String sessionName) {super(sessionName);}

    /**
     * The constructor of the tutorial. This constructor is inherited from the parent class Session
     * @param sessionName The name of the tutorial
     * @param totalVacancy The total vacancy of the tutorial
     */
    public Tutorial(String sessionName, int totalVacancy){
        super(sessionName, totalVacancy);
    }


}
