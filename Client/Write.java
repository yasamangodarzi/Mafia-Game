//package Client;
//
//import java.io.*;
//import java.net.Socket;
//import java.util.Scanner;
//
//public class Write implements Runnable {
//    private Client client;
//     private PrintWriter write;
//    private Socket socket;
//     private BufferedReader read;
////    private ObjectInputStream objectInputStream;
////    private ObjectOutputStream objectOutputStream;
//
//    public Write(Client client, Socket socket) {
//        this.client = client;
//        this.socket = socket;
//
//        try {
//             write = new PrintWriter(socket.getOutputStream(), true);
//             read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
////            objectInputStream = new ObjectInputStream(socket.getInputStream());
////            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//
//        } catch (IOException ioException) {
//            System.out.println("Error in output stream");
//            ioException.printStackTrace();
//        }
//    }
//
//    @Override
//    public void run() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println(" if you ready please Enter your name: ");
//        String userName = null;
//        while (true) {
//            userName = scanner.nextLine();
//              write.println(userName);
////            try {
////                objectOutputStream.writeUTF(userName);
////            } catch (IOException ioException) {
////                ioException.printStackTrace();
////            }
//            try {
//                  if (read.readLine().equals("t")){
//               // if (objectInputStream.readUTF().equals("t")) {
//                    client.setUserName(userName);
//                    break;
//                } else {
//                    System.out.println("your name has exist pleas try again \n Enter your name: ");
//                }
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        }
//        try {
//            String o = String.valueOf(read.ready());
//           // System.out.println(o);
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//            try {
//                System.out.println("your action: " + read.readLine());
//                System.out.println("----------------");
//
//                while (true) {
////                 Thread Read=new Thread(new ReadChat(client,socket));
////                 Read.start();
//                    try {
//                        String response = read.readLine();
//                        if (response.startsWith("Day")) {
//                            System.out.println("\n" + response);
//                            break;
//                        }
//                        System.out.println("\n" + response);
//
//                        System.out.print("");
//
//                    } catch (IOException ex) {
//                        System.out.println("Error reading from server: " + ex.getMessage());
//                        ex.printStackTrace();
//                        break;
//                    }
//                }
//                while (true) {
//                    Thread thread = new Thread(new ReadChat(client, socket));
//                    Thread thread1 = new Thread(new writeChat(socket, client));
//                    thread.start();
//                    thread1.start();
//                    //System.out.println("finish");
//                    //                 Thread Read=new Thread(new ReadChat(client,socket));
////                 Read.start();
//                }
//
////            try {
////              //  socket.close();
//////            } catch (IOException ioexception) {
//////
//////                System.out.println("Error writing to server");
//////                ioexception.printStackTrace();
//////            }
////        } catch (IOException ioException) {
////            ioException.printStackTrace();
////        }
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        }
//    }
//
