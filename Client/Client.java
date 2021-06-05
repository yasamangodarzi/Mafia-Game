package Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

        private int port;
        private String userName;

        public Client(int port) {
                this.port = port;
        }
        public void execute() {
                try {
                        Socket socket = new Socket("localhost", port);

                        System.out.println("Welcome to the Mafia game");
                        System.out.println("The mafia game is actually a game between two groups, the citizens and the mafia.\n" +
                                "The game continues in a cycle of day and night.\n" +
                                " When people present in the game try to recognize the Mafia and eliminate it from the game Mafia must hide their role altogether or distract others by distracting them. \n" +
                                "The mafia must work well together and have the right strategy for the people who want to kill at night.\n" +
                                " The main task of the citizens in this game is to realize the role of the Mafia in spite of all the lies they tell.\n");



                } catch (UnknownHostException ex) {
                        System.out.println("Server not found ");
                } catch (IOException ex) {
                        System.out.println("IOException Error");
                }

        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public static void main(String[] args) {
                Scanner scanner=new Scanner(System.in);
                Integer port = null;
                do {

                        System.out.println("\n\nPlease enter port game: ");

                        String s = scanner.nextLine();

                        try {

                                port = Integer.parseInt(s);

                        } catch (NumberFormatException e) {

                                System.out.println("ERROR: " + s + " is not a number.");
                        }

                } while (port == null);

               Client client = new  Client(port);
                client.execute();

        }


}
