package Server;

public class Die_hard extends Card{
    private int NumberOfAbilityUsage=0;
    public Die_hard() {
        action="Die_hard";
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
