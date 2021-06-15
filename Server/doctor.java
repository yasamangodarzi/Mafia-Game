package Server;

public class doctor extends Card{
    private int SaveMyself=0;
    private int Night=0;
    public doctor() {
        action="Doctor";
        setaction(action);
        setInquiry(false);
    }
    public int check()
    {
        if (Night<3){return 2;}
        else return 1;
    }

    public void setNight() {
         Night++;
    }

    public boolean getSaveMyself() {
       if (SaveMyself <1)
       {
           return false;
       }
       return true;
    }

    public void setSaveMyself() {
        SaveMyself++;
    }
}
