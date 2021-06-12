package Server;

import java.util.*;

public class Port {
    static HashMap<String,Integer> game=new HashMap<>();
    public static int port=4999;
    public   void Set(String s,Integer p)
    {
        game.put(s,p);

    }
    public int choiceGame()
    {
        System.out.println("Game :");
        Set entrySet = game.entrySet();
        Iterator it = entrySet.iterator();
        //show ready game
        System.out.println("new game please enter 0");
        while(it.hasNext()){
            Map.Entry me = (Map.Entry)it.next();
            System.out.println(  me.getKey() +
                     "  port:"+me.getValue());

        }
        Scanner scanner=new Scanner(System.in);
        Integer choice= Integer.valueOf(scanner.nextLine());
        if (choice==0){
            port++;
            System.out.println("name game:");

            Set(scanner.nextLine(),port);
            System.out.println("port game :"+port);
            return port;

        }
        else {return  choice;}



    }
    

}


