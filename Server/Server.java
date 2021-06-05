package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static  int port=5000;

    public Server(int port) {
        this.port = port;
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();

            }

        } catch (IOException ex) {
            System.out.println("Error in the server");
        }
    }

    public static void main(String[] args) {

        System.out.println("If you want a new game, enter the port :" + port);
        Scanner scanner=new Scanner(System.in);
        Integer porrt= Integer.valueOf(scanner.nextLine());
         Server server = new  Server(porrt);
        server.execute();
        
        
        

    }

}
