package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Read implements Runnable{

    private BufferedReader read;
    private Client client;
    private Socket socket;

        public Read(Client client,Socket socket) {
            this.client = client;
            this.socket = socket;

            try {
              //  InputStream input = socket.getInputStream();
                read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException ioException) {
                System.out.println("Error in input stream");
                ioException.printStackTrace();

            }
        }
    @Override
    public void run() {

    }
}
