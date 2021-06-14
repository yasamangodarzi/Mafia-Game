package Client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class writeChat implements Runnable {
        private PrintWriter writer;
        private Socket socket;
        private Client client;

        public writeChat(Socket socket, Client client) {
            this.socket = socket;
            this.client = client;

            try {
                OutputStream output = socket.getOutputStream();
                writer = new PrintWriter(output, true);
            } catch (IOException ex) {
                System.out.println("Error getting output stream: " + ex.getMessage());
                ex.printStackTrace();
            }
        }

        public void run() {

            Scanner scanner = new Scanner(System.in);
            String text=null;
            do {
                // System.out.println("[" + userName + "]: ");
                text = scanner.nextLine();
                System.out.println(text);
                writer.println(text);

            } while (!text.equals("1"));

            try {
                socket.close();
            } catch (IOException ex) {

                System.out.println("Error writing to server: " + ex.getMessage());
            }
        }
    }

