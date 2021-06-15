package Server;
/**
 * This class create a Mayor and has its features
 */
public class Mayor extends Card{
    /*
     Mayor has limited ability to use the Cancel voting
     and can only use his ability twice
     This field holds the number of uses of Mayor ability
  */
    private int NumberOfAbilityUsage=0;
    //constructor
    public Mayor() {
        // Set the role of a Mayor
        action="Mayor";
        setaction(action);
        /*
        Mayor have a negative inquiry
        Set negative inquiry
        */
        setInquiry(false);
    }
    /**
     * Checks if the Mayor can still use ability return true,
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
