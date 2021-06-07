package Server;

import java.io.*;
import java.net.Socket;

public class PlayerThread implements Runnable{
    private Socket socket;
    private  Server server;
    private PrintWriter write;


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
            String userName=null;
            do {
            userName = read.readLine();
         }while (server.check(userName));

        server.addPlayer(userName,socket);
        System.out.println("----------------------------------------------");
        server.f();
        socket.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
