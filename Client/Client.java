package Client;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * This class is a relationship between these players and the client
 * The type Client.
 */
public class Client {
        //port game
        private int port;
        private PrintWriter write;
        private Socket socket;
        private BufferedReader read;
        private String userName;
        private boolean aBoolean=true;
        private String action;
        private boolean vote=true;
        private boolean Night=true;
        /**
         * Instantiates a new Client.
         *
         * @param port the port
       */
       public Client(int port) {
                this.port = port;
        }

       /**
        * this methode execute client
        * Execute.
        */
    public void execute() {
                try {
                    // Connected to server
                          socket = new Socket("localhost", port);
                        try {
                                write = new PrintWriter(socket.getOutputStream(), true);
                                read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        } catch (IOException ioException) {
                                System.out.println("Error in output stream");
                                ioException.printStackTrace();
                        }
                        // print Menu
                        System.out.println("Welcome to the Mafia game");
                        System.out.println("The mafia game is actually a game between two groups, the citizens and the mafia.\n" +
                                "The game continues in a cycle of day and night.\n" +
                                "When people present in the game try to recognize the Mafia and eliminate it from the game Mafia must hide their role altogether or distract others by distracting them. \n" +
                                "The mafia must work well together and have the right strategy for the people who want to kill at night.\n" +
                                "The main task of the citizens in this game is to realize the role of the Mafia in spite of all the lies they tell.\n");
                        Scanner scanner = new Scanner(System.in);
                        // get name
                        System.out.println(" if you ready please Enter your name: ");
                        String userName = null;
                        while (true) {
                                userName = scanner.nextLine();
                                write.println(userName);
                                try {
                                        if (read.readLine().equals("t")){
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
                        } catch (IOException ioException) {
                                ioException.printStackTrace();
                        }
                        try {
                                action=read.readLine();
                                System.out.println("your action: " +action);
                                System.out.println("----------------");
                                while (aBoolean) {
                                    //Introduction night
                                        try {
                                                String response = read.readLine();
                                                if (response.startsWith("Day")) {
                                                        System.out.println("\n" + response);
                                                        System.out.println(read.readLine());
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
                                // day
                               //create chat room
                                while (true) {
                                        Thread thread = new Thread(new ReadChat(this, socket));
                                        Thread thread1 = new Thread(new writeChat(socket, this));
                                        thread1.start();
                                        thread.start();
                                        //vote
                                        while (vote) {
                                                try {
                                                        String response = read.readLine();
                                                                System.out.println("\n" + response);
                                                                write.println(scanner.nextLine());
                                                                System.out.println(read.readLine());
                                                                // mayor ability
                                                                if (action.equalsIgnoreCase("Mayor"))
                                                                {
                                                                       while (true)
                                                                       {
                                                                               String s=read.readLine();
                                                                               System.out.println(s);
                                                                               if (s.startsWith("Do"))
                                                                               {
                                                                                       write.println(scanner.nextLine());
                                                                                       break;
                                                                               }
                                                                       }
                                                                }
                                                    System.out.println(read.readLine());
                                                    System.out.println(read.readLine());
                                                                vote=false;
                                                } catch (IOException ex) {
                                                        System.out.println("Error reading from server: " + ex.getMessage());
                                                        ex.printStackTrace();

                                                }
                                        }
                                        // night
                                        while (Night)
                                        {

                                             if (action.equalsIgnoreCase("doctor"))
                                             {
                                                     String str=null;
                                                     System.out.println(read.readLine());
                                                    do {
                                                            str=read.readLine();
                                                             System.out.println(str);
                                                             write.println(scanner.nextLine());

                                                     }while (!(str.equalsIgnoreCase("finish")));

                                             }
                                             if (action.equalsIgnoreCase("detective"))
                                             {
                                                     System.out.println(read.readLine());
                                                     write.println(scanner.nextLine());
                                                     System.out.println(read.readLine());
                                             }
                                             if (action.equalsIgnoreCase("Die_hard"))
                                             {
                                                  System.out.println(read.readLine());
                                                  write.println(scanner.nextLine());
                                                  System.out.println(read.readLine());

                                             }
                                             if (action.equalsIgnoreCase("professional"))
                                             {
                                                     System.out.println(read.readLine());
                                                     String choice=scanner.nextLine();
                                                     write.println(choice);
                                                     if (choice.equals("1"))
                                                     {
                                                             System.out.println(read.readLine());
                                                             write.println(scanner.nextLine());
                                                     }else {System.out.println(read.readLine());}



                                             }
                                             if (action.equalsIgnoreCase("Psychologist"))
                                             {
                                                   System.out.println(read.readLine());
                                                   write.println(scanner.nextLine());
                                                     String choice=scanner.nextLine();
                                                     write.println(choice);
                                                     if (choice.equals("1"))
                                                     {
                                                             System.out.println(read.readLine());
                                                             write.println(scanner.nextLine());
                                                     }else {System.out.println(read.readLine());}



                                             }
                                             if (action.equalsIgnoreCase("Dr_Lecter"))
                                             {
                                                 System.out.println(read.readLine());
                                                 write.println(scanner.nextLine());
                                                 System.out.println(read.readLine());
                                                 System.out.println(read.readLine());
                                                 write.println(scanner.nextLine());
                                                 System.out.println(read.readLine());
                                                 write.println(scanner.nextLine());

                                             }
                                             if (action.equalsIgnoreCase("GodFather"))
                                             {
                                                  System.out.println(read.readLine());
                                                  write.println(scanner.nextLine());
                                                 System.out.println(read.readLine());
                                                 System.out.println(read.readLine());
                                                 write.println(scanner.nextLine());

                                             }
                                             if (action.equalsIgnoreCase("SimpleMafia"))
                                             {
                                                 System.out.println(read.readLine());
                                                 write.println(scanner.nextLine());
                                                 System.out.println(read.readLine());
                                                 System.out.println(read.readLine());
                                                 write.println(scanner.nextLine());
                                             }
                                             Night=false;
                                        }

                                }
                        } catch (IOException ioException) {
                                ioException.printStackTrace();
                        }
                } catch (UnknownHostException unknownHostException) {
                        unknownHostException.printStackTrace();
                } catch (IOException ioException) {
                        ioException.printStackTrace();
                }
        }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
                this.userName = userName;
        }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
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
