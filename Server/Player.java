package Server;

import java.net.Socket;
import java.util.Objects;




/**
 * This class create and manages a player
 * The type Player.
 */
public class Player {
    //namePlayer
    private String NamePlayer;
    //socket player
    private Socket socket;
    // card player
    private Card card;
    //The following Boolean variable indicates the power of speaking
    private boolean talking;
    //The following Boolean variable indicates the power of  See chats
    private boolean listen;
    //If the player is in the game, it is true, otherwise it is false
    private boolean alive;
    //If the doctor has saved the player, it will be true, otherwise it is false
    private boolean Save;

    /**
     * Instantiates a new Player.
     *
     * @param card the card
     */
    public Player(Card card) {
        this.card = card;
        talking=true;
        listen=true;
        alive=true;
        Save=false;

    }

    /**
     * Is save boolean.
     *
     * @return the boolean
     */
    public boolean isSave() {
        return Save;
    }

    /**
     * Sets save.
     *
     * @param save the save
     */
    public void setSave(boolean save) {
        this.Save = save;
    }

    /**
     * get Is talking boolean.
     *
     * @return the boolean
     */
    public boolean isTalking() {
        return talking;
    }

    /**
     * get Is listen boolean.
     *
     * @return the boolean
     */
    public boolean isListen() {
        return listen;
    }

    /**
     * get Is alive boolean.
     *
     * @return the boolean
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Sets talking.
     *
     * @param talking the talking
     */
    public void setTalking(boolean talking) {
        this.talking = talking;
    }

    /**
     * Sets listen.
     *
     * @param listen the listen
     */
    public void setListen(boolean listen) {
        this.listen = listen;
    }

    /**
     * Sets alive.
     *
     * @param alive the alive
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Sets name player.
     *
     * @param namePlayer the name player
     */
    public void setNamePlayer(String namePlayer) {
        NamePlayer = namePlayer;
    }

//    /**
//     * Sets socket.
//     *
//     * @param socket the socket
//     */
//    public void setSocket(Socket socket) {
//        this.socket = socket;
//    }

    /**
     * Gets name player.
     *
     * @return the name player
     */
    public String getNamePlayer() {
        return NamePlayer;
    }

//    /**
//     * Gets socket.
//     *
//     * @return the socket
//     */
//    public Socket getSocket() {
//        return socket;
//    }

    /**
     * Gets action card.
     *
     * @return the action card
     */
    public  String getactionCard() {
        return card.getAction();
    }

    /**
     * Gets card.
     *
     * @return the card
     */
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
