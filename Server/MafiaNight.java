package Server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class MafiaNight {
    private static ArrayList<PlayerThread> mafia=new ArrayList<>();
    public void add(PlayerThread playerThread)
    {
        mafia.add(playerThread);

    }
    public void Remove(PlayerThread playerThread)
    {
        Iterator iterator= mafia.iterator();
        PlayerThread player=null;
        while (iterator.hasNext())
        {
            player=(PlayerThread) iterator.next();
            if (player.equals(playerThread))
            {
                mafia.remove(playerThread);
                break;
            }

        }
    }
    public void action()
    {
        String YourMassage=null;
        String gun=null;
        PlayerThread gunTheard=null;
        for (PlayerThread p:mafia) {
            if (p.getcardplayer().getAction().equalsIgnoreCase("GodFather")){gun=p.getPlayer().getNamePlayer();gunTheard=p;}
            else if (p.getcardplayer().getAction().equalsIgnoreCase("Dr_Lecter")){gun=p.getPlayer().getNamePlayer();gunTheard=p;}
            else { Random rand = new Random();
                int tmp = rand.nextInt(mafia.size());
                 gun= mafia.get(tmp).getPlayer().getNamePlayer();gunTheard=mafia.get(tmp);}
        }
        do {
            for (PlayerThread p:mafia) {
                p.sendMessage("The mafia group wakes up\n"+"Consultation time\n" +
                        "Which player do you want to shoot?\n" +
                        "The gun is in  "+gun+" hand");
            }
        }while ()
    }
}
