package Server;
import java.util.*;

/**
 * This class creates and manages a poll
 */
public class Voting {
    //  A hash map that stores the names of players and their votes
    private HashMap<String,Integer>vote=new HashMap<>();

    /**
     * The methode adds the names of the players
     * @param name
     */
    public void add(String name)
    {
        vote.put(name,0);
    }

    /**
     * This mathode take the name of the player and add one to the vote
     * @param string Player name
     */
    public void vote(String string)
    {
              vote.computeIfPresent(string,(key, val) -> val + 1);
    }

    /**
     *  Shows the voting result by iterator in hashmap
     */
    public String ResultVote()
    {
        String Name=null;
        Integer Max=0;
        Set entrySet =vote.entrySet();
        Iterator it = entrySet.iterator();
        while(it.hasNext()){
            Map.Entry me = (Map.Entry)it.next();
           if (Max < (Integer) me.getValue())
           {
               Max=(Integer) me.getValue();
               Name= (String) me.getKey();

        }
    }
        return Name;
    }

    }
