package Server;
/**
 * This class creates another set of cards called Citizens
 */
public class Citizen extends Card{
    //constructor
    public Citizen() {
        // Set the role of a simple citizen
        action="Citizen";
        setaction(action);
          /*
         Citizens have a negative inquiry
        Set negative inquiry
         */
        setInquiry(false);
    }
}
