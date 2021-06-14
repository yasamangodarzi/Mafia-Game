package Client;

import java.io.*;
import java.net.Socket;

public class ReadChat implements Runnable{

    private BufferedReader read;
    private Client client;
    private Socket socket;

        public ReadChat(Client client, Socket socket) {
            this.client = client;
            this.socket = socket;

            try {
                 read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException ioException) {
                System.out.println("Error in input stream");
                ioException.printStackTrace();

            }
        }
    @Override
    public void run() {
        while (true) {
            try {
                String response = read.readLine();
                System.out.println("\n" + response);
                if (response.startsWith("finish"))
                {
                    System.out.println("Please enter 1 to close your eyes");
                    break;
                }
                    System.out.print("");
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}
