package Server;
import java.io.*;
import java.net.Socket;
import java.util.Objects;

/**
 * This class manages client
 * by creating one thread
 */
public class PlayerThread  implements Runnable{
    private Socket socket;
    private  Server server;
    private PrintWriter write;
    private BufferedReader read;
    private Player player;
    /**
     * The Day of game.
     */
    int Day = 1;
    /**
     * The Night of night.
     */
    int Night = 1;
    boolean aBoolean=true;
    boolean isaBoolean=true;
    /**
     * Instantiates a new Player thread.
     *
     * @param server the server
     * @param socket the socket
     */
    public PlayerThread(Server server,Socket socket ) {
        this.server = server;
        this.socket = socket;
    }
    @Override
    public void run() {
        InputStream input = null;
        String username="";
        try {
         read = new BufferedReader(new InputStreamReader(socket.getInputStream()));;
         write = new PrintWriter(socket.getOutputStream(), true);
         //get username
             while (true)
             {
                 String userName = read.readLine();
                 if (!(server.CheckName(userName))){ server.addName(userName);username=userName; write.println("t");
                   break;}
                 else {write.println("f");}
             }
                   //create player
                   Player player1=server.getplayer();
                   server.removeplayer(player1);
                   setPlayer(player1);
                   player.setNamePlayer(username);
                   write.println(player.getactionCard());
                   server.addplayergame(player,this);
                   server.setReadyplayer();
                   // Introduction night
                   while (true)
                   {
                       //check start game
                       if (server.start()) {
                           server.sendMassage("Introduction night");
                           server.ActionOnIntroductionNight();
                           // game loop
                            while (server.EndGameCondition()) {
                                //day game
                               server.sendMassage("Day " + Day);
                               //Inquire game
                               server.InquiringPlayerOut();
                               Day++;
                               long Time = System.currentTimeMillis();
                               long EndDay = Time + 300000;//5 min
                               String YourMassage =null;
                               // start day on 5 min
                                // create chatroom
                               do {
                                   YourMassage = read.readLine();
                                   server.sendMassage(player.getNamePlayer() + " : " + YourMassage);
                               }while (System.currentTimeMillis()<EndDay);
                               server.sendMassage("finish");
                                // start vote on 30 second
                                // create voting System
                               long Timee = System.currentTimeMillis();
                               long EndVote = Timee + 30000;//30 second
                               while (System.currentTimeMillis() < EndDay) {
                                 server.addNameToVote();
                                 server.sendMassage("Voting time\n" +
                                         "Please enter the name of the player you want");
                                   String YourVote = read.readLine();
                                 server.VotePlayer(YourVote);
                               }
                               // check Mayor
                              String playerOut= server.ResultVote();
                                server.sendMassage("The player with the most votes"+playerOut);
                               if (server.AskMayor())
                               {
                                   server.sendMassage("The mayor canceled the voting");
                               }else
                               {
                                   server.sendMassage("The mayor did not cancel the vote and "+ playerOut+" is out of the game\"");
                                   server.PlayerOut(playerOut);
                               }
                               //night game
                                server.ActionOnNight();
                           }
                           break;
                       }

                   }
        // close Socket
        socket.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
    /**
     * Sets player.
     *
     * @param player the player
     */
    public void setPlayer( Player player) {
        this.player = player;
    }
    /**
     * Send message.
     *
     * @param message the message
     */
    void sendMessage(String message) {
          write.println(message);
    }
    /**
     * Get message string.
     *
     * @return the string
     */
    String GetMessage() {
         try {
             return read.readLine();
         } catch (IOException ioException) {
             ioException.printStackTrace();
         }
         return "";
    }
    /**
     * Gets player.
     *
     * @return the player
     */
    public  Player getPlayer() {
        return player;
    }
    /**
     * Get card player .
     *
     * @return the card
     */
    public Card getcardplayer(){return player.getCard();}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerThread that = (PlayerThread) o;
        return Objects.equals(socket, that.socket) && Objects.equals(server, that.server) && Objects.equals(write, that.write)
                && Objects.equals(read, that.read) && Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socket, server, write, read, player);
    }
}
