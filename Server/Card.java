package Server;
/**
 * This class makes a card.
 * In the Mafia game,the cards are divided into two categories
 * Citizen and Mafia.
 */
public class Card {
    //The role of each card
    public String action=null;
    //Inquire any card
    private boolean Inquiry;
    /**
     *   This method sets the role of each card
     * @param s  The role of the card
     */
    public void setaction(String s)
    {
        action=s;
    }
    /**
     *  This method adjusts the card query according to its role
     * @param b Inquiry card
     */
    public void setInquiry(boolean b) {
        Inquiry=b;
    }

    /**
     * This  method returns the role of each card
     * @return string  role of each card
     */
    public String getAction() {
        return action;
    }

    /**
     *    This method returns the query for each card
     * @return If the Mafia is right
     *         If he is a citizen and the head of the Mafia, it is wrong
     */
    public boolean getInquiry() {
        return Inquiry;
    }
}





