package Server;
/**
 * This class create a doctor and has its features
 */
public class doctor extends Card{
    /*
     The doctor can save himself only once in the game
    This field stores the number of times your doctor has saved
     */
    private int SaveMyself=0;
    /*
     The doctor can save two people in the first two nights and
      only one person has to be saved the rest of the nights
        This field holds the number of game nights
     */
    private int Night=0;
    //constructor
    public doctor() {
        // Set the role of a doctor
        action="Doctor";
        setaction(action);
         /*
        doctor have a negative inquiry
        Set negative inquiry
        */
        setInquiry(false);
    }
    /**
     * hecks the number 2 if it returns overnight from the last game
     * and the number 1 otherwise
     * @return   The number of people the doctor can save
     */
    public int check()
    {
        if (Night<3){return 2;}
        else return 1;
    }
    /**
        Increase the number of Night
     */
    public void setNight() {
         Night++;
    }

    /**
     * This method checks if the doctor has saved himself once
     * the return false
     * otherwise return true
     * @return
     */
    public boolean getSaveMyself() {
       if (SaveMyself <1)
       {
           return true;
       }
       return false;
    }
   /**
      Increase the number of times your doctor saves
   */
    public void setSaveMyself() {
        SaveMyself++;
    }
}
