package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Server {
    private static  int port=5000;
    private static  Set<PlayerThread> playerThreads = new HashSet<>();
    private  static  GameManagement gameManagement=new GameManagement();
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

        System.out.println("If you want a new game, enter the port :" + port);
        Scanner scanner=new Scanner(System.in);
        Integer porrt= Integer.valueOf(scanner.nextLine());
        System.out.println("How many players do you want to play with?");
        Integer number=0;
         do {
             number= Integer.valueOf(scanner.nextLine());
         }while (gameManagement.creatPlayer(number));
         Server server = new  Server(porrt);
        server.execute();


        gameManagement.s();

    }
   public void addPlayer(String PlayerName,Socket socket) {
        boolean cor=true;
        do {
            Player player = gameManagement.getplayer();
            if (player.getNamePlayer().equals(null)) {
                player.setNamePlayer(PlayerName);
                player.setSocket(socket);
                cor=false;
            }

        }while (cor) ;

    }
     public boolean check(String name)
     {
        return  gameManagement.checkname(name);
     }
    public void f()
    {
        gameManagement.s();
    }


}
