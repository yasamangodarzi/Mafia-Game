package Server;

public class Psychologist extends Card{
    private int NumberOfAbilityUsage=0;

    public Psychologist() {
        action="Psychologist";
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
