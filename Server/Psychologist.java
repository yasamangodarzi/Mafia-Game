package Server;
/**
 * This class create a Psychologist and has its features
 */
public class Psychologist extends Card{
    /*
  Psychologist has limited ability to use Silence the players
  and can only use his ability twice
  This field holds the number of uses of Psychologist ability
*/
    private int NumberOfAbilityUsage=0;
    //constructor
    public Psychologist() {
        // Set the role of a Psychologist
        action="Psychologist";
        setaction(action);
         /*
        Psychologist have a negative inquiry
        Set negative inquiry
        */
        setInquiry(false);
    }
    /**
     * Checks if the Psychologist  can still use ability return true,
     * otherwise it returns false
     * @return
     */
    public boolean CanAbilityUsage() {
        if (NumberOfAbilityUsage==2)
        {
            return false;
        }
        return true;
    }
    /**
     * Increase the number of uses of your ability
     */
    public void setNumberOfAbilityUsage() {
        NumberOfAbilityUsage++;
    }
}
