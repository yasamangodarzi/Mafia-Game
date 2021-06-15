package Server;
/**
 * This class create a professional and has its features
 */
public class professional extends Card{
    //constructor
    public professional() {
        // Set the role of a professional
        action="professional";
        setaction(action);
           /*
        professional have a negative inquiry
        Set negative inquiry
         */
        setInquiry(false);
    }
}
