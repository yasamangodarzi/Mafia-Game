package Server;
/**
 * This class create a Dr_Lecter and has its features
 */
public class Dr_Lecter extends mafia{
    /*
   The Dr_Lecter can save himself only once in the game
  This field stores the number of times your Dr_Lecter has saved
   */
    private int SaveMyself=0;
    //constructor
    public Dr_Lecter() {
        // Set the role of a Dr_Lecter
        action="Dr_Lecter";
        setaction(action);
        /*
        Dr_Lecter have a positive inquiry
        Set Positive inquiry
         */
        setInquiry(true);
    }

    /**
     * This method checks if the Dr_Lecter has saved himself once
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
     Increase the number of times your Dr_Lecter saves
     */
    public void setSaveMyself() {
        SaveMyself++;
    }

}
