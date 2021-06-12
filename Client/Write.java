package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Write implements Runnable {
    private Client client;
    private PrintWriter write;
    private Socket socket;
    private BufferedReader read;

    public Write(Client client, Socket socket) {
        this.client = client;
        this.socket = socket;

        try {
            write = new PrintWriter(socket.getOutputStream(), true);
            read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ioException) {
            System.out.println("Error in output stream");
            ioException.printStackTrace();
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" if you ready please Enter your name: ");
        String userName = null;
        while (true) {
            userName = scanner.nextLine();
            write.println(userName);
            try {
                if (read.readLine().equals("t")) {
                    client.setUserName(userName);
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
            System.out.println(o);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            System.out.println("your action: " + read.readLine());
            System.out.println("----------------");
//                while (true)
//                {
//                   String f= read.readLine();
//
//                   if (!(f.equals("f")))
//                   {
//                       System.out.println(f);
//                       break;
//                   }
//                }
//
//               System.out.println("----------------");
////                System.out.println(read.readLine());
//
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
            while (true) {
                try {
                    String response = read.readLine();
                    System.out.println("\n" + response);

                    // prints the username after displaying the server's message
//                    if (client.getUserName() != null) {
                      System.out.print("");
                    //}
                } catch (IOException ex) {
                    System.out.println("Error reading from server: " + ex.getMessage());
                    ex.printStackTrace();
                    break;
                }
            }

            try {
                socket.close();
            } catch (IOException ioexception) {

                System.out.println("Error writing to server");
                ioexception.printStackTrace();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
