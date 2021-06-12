package Server;

import java.io.*;
import java.net.Socket;

public class PlayerThread  implements Runnable{
    private Socket socket;
    private  Server server;
    private PrintWriter write;
    private Player player;


    public PlayerThread(Server server,Socket socket ) {
        this.server = server;
        this.socket = socket;
    }
    @Override
    public void run() {
        InputStream input = null;
        String username="";
        try {
        BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        write = new PrintWriter(socket.getOutputStream(), true);
//             String userName=null;
             while (true)
             {
                 String userName = read.readLine();
                 if (!(server.CheckName(userName))){ server.addName(userName);username=userName;write.println("t");break;}
                 else {write.println("f");}
             }

                   Player player1=server.getplayer();
                   server.removeplayer(player1);
                   setPlayer(player1);
                   player.setNamePlayer(username);
                   write.println(player.getactionCard());
                   server.addplayergame(player,this);
                   server.setReadyplayer();
                   while (true)
                   {
                       if (server.start()) {

                           server.sendMassage("Introduction night");
                           server.ActionOnIntroductionNight();
                           while (server.EndGameCondition()) {
                               int Day = 1;
                               int Night = 1;
                               long Time = System.currentTimeMillis();
                               long EndDay = Time + 300000;//5 min
                               while (System.currentTimeMillis() < EndDay) {
                                   server.sendMassage("Day " + Day);
                                   Day++;
                                   String YourMassage = read.readLine();
                                   server.sendMassage(player.getNamePlayer() + " : " + YourMassage);
                               }
                               long Timee = System.currentTimeMillis();
                               long EndVote = Timee + 30000;//30 second
                               while (System.currentTimeMillis() < EndDay) {

                               }
                           }
                           break;
                       }
                   }

        socket.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public void setPlayer( Player player) {
        this.player = player;
    }
    void sendMessage(String message) {
        write.println(message);
    }

    public  Player getPlayer() {
        return player;
    }

    //    public void String java.lang.String sendAction(){return player.getactionCard();}
    public Card getcardplayer(){return player.getCard();}
    public String ismafia(){
        if (player.getCard() instanceof mafia)
        return player.getNamePlayer();
       else{return  "";}
    }
}
