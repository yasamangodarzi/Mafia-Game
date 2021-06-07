package Server;

import java.net.Socket;

public class Player {
    private String NamePlayer;
    private Socket socket;
    private Card card;

    public Player(Card card) {
        this.card = card;
    }

    public void setNamePlayer(String namePlayer) {
        NamePlayer = namePlayer;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getNamePlayer() {
        return NamePlayer;
    }

    public Socket getSocket() {
        return socket;
    }

    public  Card getCard() {
        return card;
    }

    @Override
    public String toString() {
        return "Player{" +
                "NamePlayer='" + NamePlayer + '\'' +
                ", socket=" + socket +
                ", card=" + card +
                '}';
    }
}
