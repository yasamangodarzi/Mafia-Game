package Server;
/**
 * This class create a Die_hard and has its features
 */
public class Die_hard extends Card{
    /*
        Die_hard has limited ability to use the Inquiry player outside game
        and can only use his ability twice
        This field holds the number of uses of die_hard ability
     */
    private int NumberOfAbilityUsage=0;
    //constructor
    public Die_hard() {
        // Set the role of a Die_hard
        action="Die_hard";
        setaction(action);
          /*
        Die_hard have a negative inquiry
        Set negative inquiry
         */
        setInquiry(false);
    }
    /**
     * Checks if the die-hard can still inquire return true,
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
