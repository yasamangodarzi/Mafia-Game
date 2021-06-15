package Server;
import java.util.*;
/**
 * This class manages the composed games
 */
public class Port {
    //The hashmap field below maps the names of the games to their ports
    static HashMap<String,Integer> game=new HashMap<>();
    //default port
    public static int port=4999;
    /**
     * This method takes the name of the game
     * and its port and saves it in the hash map
     * @param s name game
     * @param p port game
     */
    public   void Set(String s,Integer p)
    {
        game.put(s,p);
    }
    /**
     * This method returns the dedicated port by
     * asking the user and naming the game
     * @return port game
     */
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
        //player want new game
        if (choice==0){
            //  If the user starts a new game,
            //  it returns a special port by default
            port++;
            System.out.println("name game:");

            Set(scanner.nextLine(),port);
            System.out.println("port game :"+port);
            return port;

        }
        else {return  choice;}
    }
}


