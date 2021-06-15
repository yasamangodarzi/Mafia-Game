package Client;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * this class
 * Designed for writing from the server during chat rooms
 * This class by taking a socket and making the writer on it
 * Makes reading possible in chat rooms
 */
public class writeChat implements Runnable {
        private PrintWriter writer;
        private Socket socket;
        private Client client;

    /**
     * Instantiates a new Write chat.
     *
     * @param socket the socket
     * @param client the client
     */
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
                //write on chat room
                text = scanner.nextLine();
                System.out.println(text);
                //send  massage on chat room
                writer.println(text);
            } while (!text.equals("1"));
            try {
                //close socket
                socket.close();
            } catch (IOException ex) {
                System.out.println("Error writing to server: " + ex.getMessage());
            }
        }
    }

