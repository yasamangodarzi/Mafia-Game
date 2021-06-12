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
        try {
        BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        write = new PrintWriter(socket.getOutputStream(), true);
//             String userName=null;
             while (true)
             {
                 String userName = read.readLine();
                 if (!(server.CheckName(userName))){ server.addName(userName);write.println("t");break;}
                 else {write.println("f");}
             }

                   Player player1=server.getplayer();
                   server.removeplayer(player1);
                   setPlayer(player1);
                   write.println(player.getactionCard());
                   server.addplayergame(player,this);
                   server.setReadyplayer();
                   while (true)
                   {
                       if (server.start())
                       {
                           write.println("Introduction night");
                           server.sendMassage( "kli");
                           break;
                       }
//                       else {write.println("f");}
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
//    public void String java.lang.String sendAction(){return player.getactionCard();}
    public Card getcardplayer(){return player.getCard();}
    public String ismafia(){
        if (player.getCard() instanceof mafia)
        return player.getNamePlayer();
       else{return null;}
    }
}
