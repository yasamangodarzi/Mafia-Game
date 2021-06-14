package Server;

import java.io.*;
import java.net.Socket;

public class PlayerThread  implements Runnable{
    private Socket socket;
    private  Server server;
//    private ObjectOutputStream objectOutputStream;
//    private ObjectInputStream objectInputStream;
     private PrintWriter write;
    BufferedReader read;
    private Player player;
    int Day = 1;
    int Night = 1;
    boolean aBoolean=true;
    boolean isaBoolean=true;
    public PlayerThread(Server server,Socket socket ) {
        this.server = server;
        this.socket = socket;
    }
    @Override
    public void run() {
        InputStream input = null;
        String username="";
        try {
         read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        objectInputStream =new ObjectInputStream(socket.getInputStream());
//        objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
         write = new PrintWriter(socket.getOutputStream(), true);
//             String userName=null;
             while (true)
             {
                 String userName = read.readLine();
                 if (!(server.CheckName(userName))){ server.addName(userName);username=userName; write.println("t");
//                      objectOutputStream.writeUTF("t");
                   break;}
                 else {write.println("f");}
             }

                   Player player1=server.getplayer();
                   server.removeplayer(player1);
                   setPlayer(player1);
                   player.setNamePlayer(username);
                   write.println(player.getactionCard());
                   //objectOutputStream.writeUTF(player.getactionCard());
                   server.addplayergame(player,this);
                   server.setReadyplayer();
                   while (true)
                   {
                       if (server.start()) {
                           server.sendMassage("Introduction night");
                           server.ActionOnIntroductionNight();
                            while (server.EndGameCondition()) {
                               server.sendMassage("Day " + Day);
                               Day++;
                               long Time = System.currentTimeMillis();
                               long EndDay = Time + 300000;//5 min
                               String YourMassage =null;
                               do {

                                      YourMassage = read.readLine();
                                   //String YourMassage = objectInputStream.readUTF();
                                   server.sendMassage(player.getNamePlayer() + " : " + YourMassage);
                               }while (System.currentTimeMillis()<EndDay);
                               server.sendMassage("finish");

                               long Timee = System.currentTimeMillis();
                               long EndVote = Timee + 30000;//30 second
                               while (System.currentTimeMillis() < EndDay) {
                                 server.addNameToVote();
                                 server.sendMassage("Voting time\n" +
                                         "Please enter the name of the player you want");
                                   String YourVote = read.readLine();
                                 server.VotePlayer(YourVote);
                               }
                              String playerOut= server.ResultVote();
                                server.sendMassage("The player with the most votes"+playerOut);
                               if (server.AskMayour())
                               {
                                   server.sendMassage("The mayor canceled the voteing");
                               }else
                               {
                                   server.sendMassage("The mayor did not cancel the vote and "+ playerOut+" is out of the game\"");
                                   server.PlayerOut(playerOut);
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
//        try {
//            objectOutputStream.writeUTF(message);
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
    }
     String GetMessage() {
         try {
             return read.readLine();
         } catch (IOException ioException) {
             ioException.printStackTrace();
         }
         return "";
    }

    public  Player getPlayer() {
        return player;
    }

    //    public void String java.lang.String sendAction(){return player.getactionCard();}
    public Card getcardplayer(){return player.getCard();}
//    public String ismafia(){
//        if (player.getCard() instanceof mafia)
//        return player.getNamePlayer();
//       else{return  "";}
//    }
}
