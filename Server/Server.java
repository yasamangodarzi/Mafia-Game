package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * This class create the game server or Gad game and controls the overall game
 */
public class Server {
    // port server
    private static  int port;
    //number player
    private static Integer number=0;
    // number of readyPlayer
    private static int readyplayer=0;
    // array list for all player in game
    private ArrayList<Player> playerSet = new ArrayList<>();
    private static  Set<PlayerThread> playerThreads = new HashSet<>();
    // player name
    private static ArrayList<String>name=new ArrayList<>();
    // hold player role mafia
    private static ArrayList<PlayerThread>mafia=new ArrayList<>();
    private static HashMap<Player,PlayerThread> playergame=new HashMap<>();
    //hold Player outside game
    private static ArrayList<Player>daedPlayer=new ArrayList<>();
    //craete class port
    private static Port porrt=new Port();
    //If the die-hard inquiry request is true, otherwise it is false
    private static boolean InquiryDiehard=false;
    //create class vote
    private Voting voting;
    //constructor
    public Server(int port) {
        this.port = port;
    }
    /**
     * This method execute the server
     */
    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                // Alarms the server in a dedicated earphone
                Socket socket = serverSocket.accept();
                //The client connects to the server
                PlayerThread NewPlayer = new PlayerThread(this,socket);
                playerThreads.add(NewPlayer);
                //thread is created
                Thread thread=new Thread(NewPlayer);
                //thread is start
                thread.start();
            }

        } catch (IOException ioException) {
            System.out.println("Error in the server");
            ioException.printStackTrace();
        }
    }
    // main methode
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
    /**
     * This method adds a number to the number of ready players whenever each client declares readiness.
     */
    public  void setReadyplayer( ) {
         readyplayer++;
         System.out.println("readyplayer:"+readyplayer);
         System.out.println("number"+number);
    }
    /**
     * This method checks if the number of players entered is equal to the number of players ready to play
     * return true, otherwise it returns false.
     * @return start game
     */
    public boolean start()
    {
        if (readyplayer==number)
        {
            return true;
        }else return false;
    }
    /**
     * this methode show card
     */
    public void s()
    {
        System.out.println("Cards:");
        for (Player p: playerSet ) {
            System.out.println(p.toString());

        }
        System.out.print("number player :");
        System.out.println(playerSet.size());
    }

    /**
     * This method takes the number of player players
     *  If there are less than 8 players, the user will be re-entered
     *  Otherwise it makes cards with proportions
     * @param numberPlayer
     * @return
     */
    public boolean creatPlayer(int numberPlayer)
    {
       //
        if (numberPlayer<8){
            System.out.println("You can not play with this number of players\n pleas try again");
            return true;
        }else
        {
            // create player
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

    /**
     * This class randomly returns a player that contains a special card
     * @return
     */
    public Player getplayer() {

        Random rand = new Random();
        int tmp = rand.nextInt(playerSet.size());
        return playerSet.get(tmp);
    }

    /**
     * This method checks if the player name is duplicate and takes another name from the user
     * @param n
     * @return true if name okey
     */
    public boolean CheckName(String n)
    {
        System.out.println(n);
        boolean exist=false;
        for (String str: name ) {
            if (str.equals(n)){exist=true;}
        }
        return exist;
    }

    /**
     * This method adds the player name to the list of player names
     * @param s
     */
    public void addName(String s)
    {
        name.add(s);
    }

    /**
     * This method deletes the given player name by scrolling through the list
     * @param namePlayer
     */
    public void removeName(String namePlayer)
    {
        Iterator iterator= name.iterator();
        String string=null;
        while (iterator.hasNext())
        {
            string=(String) iterator.next();
            if (string.equalsIgnoreCase(namePlayer))
            {
                name.remove(namePlayer);
                break;
            }

        }
    }

    /**
     * this methode add player and map with playerThread
     * @param player
     * @param playerThread
     */
    public void addplayergame(Player player,PlayerThread playerThread)
    {
       playergame.put(player,playerThread);
    }

    /**
     * This method removes a player from the list
     * @param player
     */
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

    /**
     *  This method removes a playerThread from the list
     * @param playerThread
     */
    public void RemovePlayerTheard(PlayerThread playerThread)
    {
        Iterator iterator= playerThreads.iterator();
        PlayerThread playerThread1=null;
        while (iterator.hasNext())
        {
            playerThread1 =(PlayerThread) iterator.next();
            if (playerThread1.equals(playerThread))
            {
                playerThreads.remove(playerThread1);
                break;
            }

        }
    }

    /**
     * This method sends a message to all players
     * @param message
     */
    void  sendMassage(String message) {
        for (PlayerThread aUser : playerThreads) {
             if (aUser.getPlayer().isAlive() || aUser.getPlayer().isListen()) {
                aUser.sendMessage(message);
             }
        }
    }

    /**
     * This method removes a player from the game
     *   To do this, it removes the player from the player list and player name and playerThread
     *   and add to dead player
     *   Asks players if they would like to continue chatting if
     *   If the player answers yes, it shows the chat for him
     * @param string
     */
    void PlayerOut(String string)
    {
        for (PlayerThread aUser : playerThreads) {
            if (aUser.getPlayer().getNamePlayer().equalsIgnoreCase(string)) {
                aUser.getPlayer().setAlive(false);
                aUser.sendMessage("If you want to see the rest of the chats, enter 1, otherwise enter 0");
                if (aUser.GetMessage().equalsIgnoreCase("0"))
                {
                    aUser.getPlayer().setListen(false);
                }
                Player player=aUser.getPlayer();
                daedPlayer.add(player);
                removeName(player.getNamePlayer());
                removeplayer(player);
                RemovePlayerTheard(aUser);
            }
        }
    }

    /**
     * This method manages the introduction night
     * Depending on the role of each player, it sends the appropriate message to it
     * If the player has the role of Mafia, he adds it to the list of Mafias
     * It also sends the names of other Mafia players to the Mafias
     */
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
                mafia.add(u);
            }
            if (u.getcardplayer().getAction().equalsIgnoreCase("GodFather")) {

                u.sendMessage("The final shot with you and your inquiry is always negative for the detective");
                MafiaName.append(u.getPlayer().getNamePlayer() + ":" + u.getcardplayer().getAction() + "\n");
                mafia.add(u);
            }
            if (u.getcardplayer().getAction().equalsIgnoreCase("SimpleMafia")) {

                u.sendMessage("good luck");
                MafiaName.append(u.getPlayer().getNamePlayer() + ":" + u.getcardplayer().getAction() + "\n");
                mafia.add(u);
            }
            if (u.getcardplayer().getAction().equalsIgnoreCase("Mayor")) {

                u.sendMessage("You can cancel the voting two  days. Good luck\n" +
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

    /**
     * This method checks the condition of the end of the game
     * The condition for the end of the game is that the number of mafias
     * is equal to or greater than the number of citizens in the game
     * @return
     */
    public boolean EndGameCondition()
{
    if (mafia.size()>=number-mafia.size())
    {
        return false;
    }
   else{return true;}
}

    /**
     *This method adds the names of in game players to vote
     */
    public void addNameToVote()
{
    for (String n:name) {
        voting.add(n);
    }
}

    /**
      This method adds a number to the vote by naming a player
     * @param s
     */
    public void VotePlayer(String s)
{
    voting.vote(s);
}

    /**
     *   This method announces the voting result using the voting class
     * @return
     */
    public String ResultVote()
    {
       return voting.ResultVote();
    }

    /**
     * this method asks the game mayor if he wants to cancel the withdrawal
     * @return
     */
    public boolean AskMayor()
        {
    boolean use=false;
    for (PlayerThread u:playerThreads) {
        if (u.getcardplayer().getAction().equalsIgnoreCase("Mayor"))
        {
           Mayor mayor=(Mayor) u.getcardplayer();
           if (mayor.CanAbilityUsage())
           {
               u.sendMessage("Do you want to use your ability and cancel the voting? 1 (Yas) / 0 (No)");
               if (u.GetMessage().equalsIgnoreCase("1"))
               {
                   mayor.setNumberOfAbilityUsage();
                   use=true;
               }
           }else
           {
               u.sendMessage("You used your capabilities twice");
                 use=false;
           }

        }
    }
    return use;
}

    /**
     * This method takes the name of a player and returns the result of the inquiry
     * @param string
     * @return
     */
    public boolean Inquiry(String string)
{
    for (PlayerThread aUser : playerThreads) {
         if (aUser.getPlayer().getNamePlayer().equalsIgnoreCase(string))
         {
              return aUser.getPlayer().getCard().getInquiry();
         }
    }
    return true;
}

    /**
     * This method manages the properties that each role has at night
     *   And by waking up to each role and asking him for things,
     *   it performs the task of that role at night
     */
    public void ActionOnNight()
{
    boolean mafiaShoot=false;
    StringBuilder stringBuilder=new StringBuilder();
    stringBuilder.append("Players who left the game\n");
    ArrayList<PlayerThread>playerOut=new ArrayList<PlayerThread>();
    for (PlayerThread u:playerThreads) {
        u.sendMessage("Night");
        // doctor role
        if (u.getcardplayer().getAction().equalsIgnoreCase("Doctor")) {
            doctor doctor=(doctor) u.getcardplayer();
            doctor.setNight();
            u.sendMessage("What player do you want to save?");
            for (int i=0;i<=doctor.check(); )
            {
                int j=1;
                u.sendMessage("NamePlayer"+j+":");

                String SavePlayer=u.GetMessage();
                for (PlayerThread aUser : playerThreads) {
                    if (aUser.getPlayer().getNamePlayer().equals(SavePlayer))
                    {

                        if (aUser.getcardplayer().getAction().equalsIgnoreCase("Doctor"))
                        {
                           if (doctor.getSaveMyself())
                           {
                               doctor.setSaveMyself();
                               aUser.getPlayer().setSave(true);
                               i++;
                               j++;
                           }
                           else {aUser.sendMessage("You can not save yourself more than once!");}
                        }else
                        {
                            if (!(aUser.getPlayer().getCard() instanceof mafia))
                            {
                                aUser.getPlayer().setSave(true);

                            }
                            i++;
                            j++;
                        }

                    }

                }

            }
            u.sendMessage("finish");
        }
        // detective role
        if (u.getcardplayer().getAction().equalsIgnoreCase("detective")) {
            u.sendMessage("Inquire which player you want?");
            if (Inquiry(u.GetMessage())){u.sendMessage("Your inquiry is positive");}
            else {u.sendMessage("Your query is negative");}
        }
         // die hard role
        if (u.getcardplayer().getAction().equalsIgnoreCase("Die_hard")) {
            Die_hard die_hard=(Die_hard) u.getcardplayer();
            if (die_hard.CanAbilityUsage())
            {
                u.sendMessage("Do you want to inquire about people outside the game? 1 (Yas) / 0 (No)");
                if (u.GetMessage().equals("1"))
                {
                    die_hard.setNumberOfAbilityUsage();
                    InquiryDiehard=true;
                    u.sendMessage("Ok");
                }
            }else
            {
                u.sendMessage("You used your capabilities twice");
            }
        }
        if (u.getcardplayer() instanceof mafia)
        {
            // dr-lecter role
            if (u.getcardplayer().getAction().equalsIgnoreCase("Dr_Lecter")) {

                Dr_Lecter dr_lecter = (Dr_Lecter) u.getcardplayer();
                u.sendMessage("Which player do you want to shoot?");
                String YourMassage = u.GetMessage();
                sendMassageToMafia("Player comments" + u.getPlayer().getNamePlayer() + YourMassage);
                if (!mafiaShoot) {
                    u.sendMessage("Who are you shooting at?");
                    String player = u.GetMessage();
                    for (PlayerThread pl : playerThreads) {
                        if (pl.getPlayer().getNamePlayer().equalsIgnoreCase(player)) {
                            if (pl.getPlayer().isSave()) {
                                break;
                            } else {
                                PlayerOut(player);
                                playerOut.add(pl);
                            }
                        }
                    }
                    mafiaShoot = true;
                }
                u.sendMessage("Who do you want to save?");
                String mafiaSave = u.GetMessage();
                for (PlayerThread mafia : mafia) {
                    if (mafia.getPlayer().getNamePlayer().equalsIgnoreCase(mafiaSave)) {
                        if (mafia.getcardplayer().getAction().equalsIgnoreCase("Dr_Lecter")) {
                            if (dr_lecter.getSaveMyself()) {
                                dr_lecter.setSaveMyself();
                                mafia.getPlayer().setSave(true);
                            }else {mafia.sendMessage("You can not save yourself more than once!");}
                        } else mafia.getPlayer().setSave(true);
                    }
                }
            }
            // godFather role
            if (u.getcardplayer().getAction().equalsIgnoreCase("GodFather")) {

                u.sendMessage("Which player do you want to shoot?");
                String YourMassage = u.GetMessage();
                sendMassageToMafia("Player comments"+u.getPlayer().getNamePlayer()+YourMassage);
                if (u.getPlayer().isAlive())
                {
                    mafiaShoot=true;
                    u.sendMessage("Who are you shooting at?");
                   String player= u.GetMessage();
                    for (PlayerThread pl:playerThreads) {
                        if (pl.getPlayer().getNamePlayer().equalsIgnoreCase(player))
                        {
                            if (pl.getPlayer().isSave())
                            {
                                break;
                            }else {PlayerOut(player);}
                        }
                    }
                }

            }
            // simpleMafia role
            if (u.getcardplayer().getAction().equalsIgnoreCase("SimpleMafia")) {
                u.sendMessage("Which player do you want to shoot?");
                String YourMassage = u.GetMessage();
                sendMassageToMafia("Player comments"+u.getPlayer().getNamePlayer()+YourMassage);
                if (!mafiaShoot)
                {
                    u.sendMessage("Who are you shooting at?");
                    String player= u.GetMessage();
                    for (PlayerThread pl:playerThreads) {
                        if (pl.getPlayer().getNamePlayer().equalsIgnoreCase(player))
                        {
                            if (pl.getPlayer().isSave())
                            {
                                break;
                            }else {PlayerOut(player);}
                        }
                    }
                    mafiaShoot=true;
                }
            }
        }
        // professional role
        if (u.getcardplayer().getAction().equalsIgnoreCase("professional")) {
            u.sendMessage("Do you want to shoot? 1 (Yas) / 0 (No)");
            if (u.GetMessage().equalsIgnoreCase("1"))
            {
                 u.sendMessage("Please enter the player name:");
                 String shootPlyer=u.GetMessage();
                for (PlayerThread aUser : playerThreads) {
                    if (aUser.getPlayer().getNamePlayer().equalsIgnoreCase(shootPlyer))
                    {
                       if (aUser.getcardplayer() instanceof mafia)
                       {
                           if (aUser.getPlayer().isSave()){break;}
                           else {   PlayerOut(shootPlyer);
                               stringBuilder.append(shootPlyer+"\n");
                               playerOut.add(aUser);
                           }

                       }else
                       {
                           for (PlayerThread a  : playerThreads) {
                               if (a.getPlayer().getNamePlayer().equalsIgnoreCase("professional"))
                               {
                                   PlayerOut(a.getPlayer().getNamePlayer());
                                   stringBuilder.append(a.getPlayer().getNamePlayer()+"\n");
                                   playerOut.add(a);
                               }

                           }
                       }
                    }
                }
            }else {u.sendMessage("ok");}

        }
        // psychologist role
        if (u.getcardplayer().getAction().equalsIgnoreCase("Psychologist")) {
           Psychologist psychologist=(Psychologist) u.getcardplayer();
            if (psychologist.CanAbilityUsage())
            {
                u.sendMessage("Do you want to silence a player? 1 (Yas) / 0 (No)");
                if (u.GetMessage().equalsIgnoreCase("1"))
                {
                    psychologist.setNumberOfAbilityUsage();
                    u.sendMessage("Enter the name of the player you want to silence");
                    String SilentPlayer=u.GetMessage();
                    for (PlayerThread aUser : playerThreads) {
                        if (aUser.getPlayer().getNamePlayer().equalsIgnoreCase(SilentPlayer))
                        {
                           aUser.getPlayer().setTalking(false);
                        }
                    }

                }
            }else
            {
                u.sendMessage("You used your capabilities twice");
            }
        }
    }
}

    /**
     * This method shows users inquiry out-of-game characters
     * if die hard want
     */
    public void InquiringPlayerOut ()
{
    if (InquiryDiehard)
    {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("The role of out of game players\n");
        for (Player p:daedPlayer) {
            stringBuilder.append(p.getactionCard()+"\n");
        }

        for (PlayerThread aUser : playerThreads) {
            if (aUser.getPlayer().isAlive() || aUser.getPlayer().isListen())
            {
                aUser.sendMessage(stringBuilder.toString());
            }
        }
        InquiryDiehard=false;
    }
}
public void sendMassageToMafia(String s)
{
    for (PlayerThread p:mafia) {
        p.sendMessage(s);
    }
}
}
