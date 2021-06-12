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
         System.out.println(readyplayer);
         System.out.println(number);
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

                    aUser.sendMessage(message);
            }
        }

    public String sendNameMafia()
    {
        StringBuilder stringBuilder=new StringBuilder();
        for (PlayerThread u:playerThreads) {

                String name=u.ismafia();
                if (name.equals(null))
                {
                    stringBuilder.append(u.getcardplayer().getAction()+":" +"welcome to mafia Game ");
                }else {stringBuilder.append(u.getcardplayer().getAction()+":" +name);}


        }
        return stringBuilder.toString();
    }




}
