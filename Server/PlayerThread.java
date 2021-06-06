package Server;

import java.io.PrintWriter;
import java.net.Socket;

public class PlayerThread implements Runnable{
    private Socket socket;
    private  Server server;
    private PrintWriter writer;

    public PlayerThread(Server server,Socket socket ) {
        this.server = server;
        this.socket = socket;
    }
    @Override
    public void run() {

    }
}
