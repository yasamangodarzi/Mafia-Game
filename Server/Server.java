package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    private static  int port=5000;
     static Integer number=0;
    private static int readyplayer=0;
    private ArrayList<Player> playerSet = new ArrayList<>();
    private static  Set<PlayerThread> playerThreads = new HashSet<>();
    private static ArrayList<String>name=new ArrayList<>();
    private static ArrayList<Player>mafia=new ArrayList<>();
    private static HashMap<Player,PlayerThread> playergame=new HashMap<>();
     public static Port porrt=new Port();

    public Server(int port) {
        this.port = port;
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                PlayerThread NewPlayer = new PlayerThread(this,socket);
                playerThreads.add(NewPlayer);
                Thread thread=new Thread(NewPlayer);
                thread.start();

            }

        } catch (IOException ioException) {
            System.out.println("Error in the server");
            ioException.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Server server = new  Server(porrt.choiceGame());
        System.out.println("How many players do you want to play with?");
         do {
             number= Integer.valueOf(scanner.nextLine());
         }while (server.creatPlayer(number));
        server.s();
        server.execute();
    }
//   public void addPlayer(String PlayerName,Socket socket) {
//        boolean cor=true;
//        do {
//            Player player = gameManagement.getplayer();
//            if (player.getNamePlayer().equals(null)) {
//                player.setNamePlayer(PlayerName);
//                player.setSocket(socket);
//                cor=false;
//            }
//
//        }while (cor) ;
//
//    }


    public static int getReadyplayer() {
        return readyplayer;
    }

    public  void setReadyplayer( ) {
         readyplayer++;
         System.out.println("readyplayer:"+readyplayer);
         System.out.println("number"+number);
    }
    public boolean start()
    {
        if (readyplayer==number)
        {
            return true;
        }else return false;
    }


    public void s()
    {
        System.out.println("Cards:");
        for (Player p: playerSet ) {
            System.out.println(p.toString());

        }
        System.out.print("number player :");
        System.out.println(playerSet.size());
    }
    public boolean creatPlayer(int numberPlayer)
    {

        if (numberPlayer<8){
            System.out.println("You can not play with this number of players\n pleas try again");
            return true;
        }else
        {
            playerSet.add(new Player(new doctor()));
            playerSet.add(new Player(new Mayor()));
            playerSet.add(new Player(new Detective()));
            playerSet.add(new Player(new Die_hard()));
            playerSet.add(new Player(new GodFather()));
            playerSet.add(new Player(new Dr_Lecter()));
            playerSet.add(new Player(new professional()));
            playerSet.add(new Player(new Psychologist()));
            numberPlayer-=8;
            if (numberPlayer!=0)
            {  for (int t =0; t<(int) Math.ceil(numberPlayer/3)+1; t++)
            {
                playerSet.add(new Player(new SimpleMafia()));
            }
                for (int t =0; t<numberPlayer-(int) Math.ceil(numberPlayer/3)-1 ; t++)
                {
                    playerSet.add(new Player(new Citizen()));
                }

            }
            return false;
        }

    }
    public Player getplayer() {

        Random rand = new Random();
        int tmp = rand.nextInt(playerSet.size());
        return playerSet.get(tmp);
    }
    public boolean CheckName(String n)
    {
        System.out.println(n);
        boolean exist=false;
        for (String str: name ) {
            if (str.equals(n)){exist=true;}
        }
        return exist;
    }
    public void addName(String s)
    {
        name.add(s);
    }
    public void addplayergame(Player player,PlayerThread playerThread)
    {
       playergame.put(player,playerThread);
    }
    public void removeplayer(Player player)
    {
        Iterator iterator= playerSet.iterator();
        Player player1=null;
        while (iterator.hasNext())
        {
            player1=(Player)iterator.next();
            if (player1.equals(player))
            {
                playerSet.remove(player);
                break;
            }

        }
    }
    void broadcast(String message, PlayerThread excludeUser) {
        for (PlayerThread aUser : playerThreads) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }
    void broadcastforMafia(String message, PlayerThread excludeUser) {
        for (PlayerThread aUser : playerThreads) {
            if (aUser != excludeUser) {
                if (aUser.getcardplayer()instanceof mafia)
                {
                    aUser.sendMessage(message);
                }

            }
        }
    }
    void  sendMassage(String message) {
        for (PlayerThread aUser : playerThreads) {
            if (aUser.getPlayer().isAlive() && aUser.getPlayer().isListen()) {
                aUser.sendMessage(message);
            }
        }
    }

    public void ActionOnIntroductionNight()
    {
        StringBuilder MafiaName=new StringBuilder();
        String DocterName=null;
         MafiaName.append("\nGroup Mafia\n");
        for (PlayerThread u:playerThreads) {
            if (u.getcardplayer().getAction().equalsIgnoreCase("Doctor")) {
                u.sendMessage("You can save two people in two nights and only one person in the rest of the nights\n" +
                        "Good luck");
                DocterName = u.getPlayer().getNamePlayer();
            }
            if (u.getcardplayer().getAction().equalsIgnoreCase("Citizen")) {
                u.sendMessage("Good luck in choosing the Mafia");
            }
            if (u.getcardplayer().getAction().equalsIgnoreCase("detective")) {
                u.sendMessage("Be careful when inquiring\nGood luck");
            }
            if (u.getcardplayer().getAction().equalsIgnoreCase("Die_hard")) {
                u.sendMessage("You will be safe from the Mafia overnight\n" +
                        "You can also query people who have left the game twice\n" +
                        "Good luck");
            }
            if (u.getcardplayer().getAction().equalsIgnoreCase("Dr_Lecter")) {

                u.sendMessage("You can only save yourself once\n" +
                        "  But you have no limits for the rest\n" +
                        "Good luck");
                MafiaName.append(u.getPlayer().getNamePlayer() + ":" + u.getcardplayer().getAction() + "\n");
                mafia.add(u.getPlayer());
            }
            if (u.getcardplayer().getAction().equalsIgnoreCase("GodFather")) {

                u.sendMessage("The final shot with you and your inquiry is always negative for the detective");
                MafiaName.append(u.getPlayer().getNamePlayer() + ":" + u.getcardplayer().getAction() + "\n");
                mafia.add(u.getPlayer());
            }
            if (u.getcardplayer().getAction().equalsIgnoreCase("SimpleMafia")) {

                u.sendMessage("good luck");
                MafiaName.append(u.getPlayer().getNamePlayer() + ":" + u.getcardplayer().getAction() + "\n");
                mafia.add(u.getPlayer());
            }
            if (u.getcardplayer().getAction().equalsIgnoreCase("Mayor")) {

                u.sendMessage("You can cancel the voting once a day. Good luck\n" +
                        "Also Dr. Shahr: " + DocterName);
            }
            if (u.getcardplayer().getAction().equalsIgnoreCase("professional")) {

                u.sendMessage("You can shoot every night\n If you become a citizen, you will leave the game yourself" +
                        "\n And if you hit the Mafia, the Mafia will be out of the game \nGood luck");
            }
            if (u.getcardplayer().getAction().equalsIgnoreCase("Psychologist")) {

                u.sendMessage("You can choose someone to treat\n" +
                        "That way he can't talk during the day\n" +
                        "You have this feature twice\n" +
                        "Good luck");
            }

        }
        for (PlayerThread u:playerThreads) {
            if (u.getcardplayer() instanceof mafia)
            {
                u.sendMessage(MafiaName.toString());
            }
        }

    }

public boolean EndGameCondition()
{
    if (mafia.size()>=number-mafia.size())
    {
        return false;
    }
   else{return true;}
}



}
