package Server;

import java.util.*;

public class Voting {
    private HashMap<String,Integer>vote=new HashMap<>();

    public void add(String name)
    {
        vote.put(name,0);
    }
    public void vote(String string)
    {
              vote.computeIfPresent(string,(key, val) -> val + 1);
    }
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


