package Client;

import Server.Player;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

        private int port;
        private PrintWriter write;
        private Socket socket;
        private BufferedReader read;
        private String userName;
        boolean aBoolean=true;
        private String action;
        boolean vote=true;
        public Client(int port) {
                this.port = port;
        }
        public void execute() {
                try {
                          socket = new Socket("localhost", port);
                        try {
                                write = new PrintWriter(socket.getOutputStream(), true);
                                read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        } catch (IOException ioException) {
                                System.out.println("Error in output stream");
                                ioException.printStackTrace();
                        }

                        System.out.println("Welcome to the Mafia game");
                        System.out.println("The mafia game is actually a game between two groups, the citizens and the mafia.\n" +
                                "The game continues in a cycle of day and night.\n" +
                                "When people present in the game try to recognize the Mafia and eliminate it from the game Mafia must hide their role altogether or distract others by distracting them. \n" +
                                "The mafia must work well together and have the right strategy for the people who want to kill at night.\n" +
                                "The main task of the citizens in this game is to realize the role of the Mafia in spite of all the lies they tell.\n");
                        Scanner scanner = new Scanner(System.in);
                        System.out.println(" if you ready please Enter your name: ");
                        String userName = null;
                        while (true) {
                                userName = scanner.nextLine();
                                write.println(userName);
//            try {
//                objectOutputStream.writeUTF(userName);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
                                try {
                                        if (read.readLine().equals("t")){
                                                // if (objectInputStream.readUTF().equals("t")) {
                                                setUserName(userName);
                                                break;
                                        } else {
                                                System.out.println("your name has exist pleas try again \n Enter your name: ");
                                        }
                                } catch (IOException ioException) {
                                        ioException.printStackTrace();
                                }
                        }
                        try {
                                String o = String.valueOf(read.ready());
                                // System.out.println(o);
                        } catch (IOException ioException) {
                                ioException.printStackTrace();
                        }
                        try {
                                action=read.readLine();
                                System.out.println("your action: " +action);
                                System.out.println("----------------");

                                while (aBoolean) {
//                 Thread Read=new Thread(new ReadChat(client,socket));
//                 Read.start();
                                        try {
                                                String response = read.readLine();
                                                if (response.startsWith("Day")) {
                                                        System.out.println("\n" + response);
                                                       aBoolean=false;
                                                }
                                                System.out.println("\n" + response);

                                                System.out.print("");

                                        } catch (IOException ex) {
                                                System.out.println("Error reading from server: " + ex.getMessage());
                                                ex.printStackTrace();
                                                break;
                                        }
                                }
                                while (true) {
                                        Thread thread = new Thread(new ReadChat(this, socket));
                                        Thread thread1 = new Thread(new writeChat(socket, this));
                                        thread1.start();
                                        thread.start();
                                        while (vote) {
                                                try {
                                                        String response = read.readLine();
                                                        if (response.startsWith("Voting time")) {
                                                                System.out.println("\n" + response);
                                                                write.println(scanner.nextLine());
                                                                if (action.equalsIgnoreCase(""))
                                                                {
                                                                       while (true)
                                                                       {
                                                                               String s=read.readLine();
                                                                               System.out.println("\n" + response);
                                                                               if (s.startsWith("Do"))
                                                                               {
                                                                                       write.println(scanner.nextLine());
                                                                                       break;
                                                                               }
                                                                       }
                                                                }
                                                        }
                                                        System.out.println("\n" + response);
                                                        System.out.print("");
                                                        if (response.startsWith("Night"))
                                                        {
                                                                vote=false;
                                                        }

                                                } catch (IOException ex) {
                                                        System.out.println("Error reading from server: " + ex.getMessage());
                                                        ex.printStackTrace();

                                                }
                                        }

                                }

//            try {
//              //  socket.close();
////            } catch (IOException ioexception) {
////
////                System.out.println("Error writing to server");
////                ioexception.printStackTrace();
////            }
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
                        } catch (IOException ioException) {
                                ioException.printStackTrace();
                        }
                } catch (UnknownHostException unknownHostException) {
                        unknownHostException.printStackTrace();
                } catch (IOException ioException) {
                        ioException.printStackTrace();
                }
        }
//                        Thread threadread=new Thread(new Read(this,socket));
//                        threadread.start();
//                         Thread threadwrite=new Thread(new Write(this,socket));
//                         threadwrite.start();
//                } catch (UnknownHostException  unknownHostException) {
//                        System.out.println("Server not found ");
//                        unknownHostException.printStackTrace();
//                } catch (IOException ioException) {
//                        System.out.println("IOException Error");
//                        ioException.printStackTrace();
//                }



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

                        System.out.println("Please enter port game: ");

                        String s = scanner.nextLine();

                        try {

                                port = Integer.parseInt(s);

                        } catch (NumberFormatException numberFormatException) {

                                System.out.println("ERROR: " + s + " is not a number.");
                        }

                } while (port == null);

               Client client = new  Client(port);
                client.execute();

        }


}
