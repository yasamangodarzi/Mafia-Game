package Server;

import java.net.Socket;

public class Player {
    private String NamePlayer;
    private Socket socket;
    private Card card;

    public Player(Card card) {
        this.card = card;
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
