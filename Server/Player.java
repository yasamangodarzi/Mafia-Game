package Server;

import java.net.Socket;
import java.util.Objects;

public class Player {
    private String NamePlayer;
    private Socket socket;
    private Card card;
    private boolean talking;
    private boolean listen;
    private boolean alive;
    private boolean Save;

    public Player(Card card) {
        this.card = card;
        talking=true;
        listen=true;
        alive=true;
        Save=false;

    }

    public boolean isSave() {
        return Save;
    }

    public void setSave(boolean save) {
        this.Save = save;
    }

    public boolean isTalking() {
        return talking;
    }

    public boolean isListen() {
        return listen;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setTalking(boolean talking) {
        this.talking = talking;
    }

    public void setListen(boolean listen) {
        this.listen = listen;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
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

    public  String getactionCard() {
        return card.getAction();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(NamePlayer, player.NamePlayer) && Objects.equals(socket, player.socket) && Objects.equals(card, player.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NamePlayer, socket, card);
    }
}
