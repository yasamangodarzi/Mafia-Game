package Server;

public class Mayor extends Card{
    private int NumberOfAbilityUsage=0;
    public Mayor() {
        action="Mayor";
        setaction(action);
        setInquiry(false);
    }

    public boolean CanAbilityUsage() {
       if (NumberOfAbilityUsage==2)
       {
           return false;
       }
       return true;
    }

    public void setNumberOfAbilityUsage() {
        NumberOfAbilityUsage++;
    }
}
