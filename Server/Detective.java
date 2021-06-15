package Server;
/**
 * This class create a detective and has its features
 */
public class Detective extends Card {
    //constructor
    public Detective() {
        // Set the role of a Detective
        action="Detective";
        setaction(action);
        /*
        Detective have a negative inquiry
        Set negative inquiry
         */
        setInquiry(false);
    }
}
