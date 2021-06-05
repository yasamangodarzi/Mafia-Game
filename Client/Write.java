package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Write implements Runnable{
        private  Client client;
        private PrintWriter write;
        private Socket socket;


        public Write(Client client,Socket socket) {
            this.client = client;
            this.socket = socket;

            try {
                write= new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException ioException) {
                System.out.println("Error in output stream");
                ioException.printStackTrace();
            }
        }

        @Override
    public void run() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your name: ");
            String userName = scanner.nextLine();
            client.setUserName(userName);
            write.println(userName);
            try {
                socket.close();
            } catch (IOException ioexception) {

                System.out.println("Error writing to server");
                ioexception.printStackTrace();
            }
    }
}
