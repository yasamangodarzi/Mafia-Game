//package Server;
//
//import java.util.ArrayList;
//
//import java.util.*;
//public class GameManagement {
//    private ArrayList<Player> playerSet = new ArrayList<>();
//    public boolean creatPlayer(int numberPlayer)
//    {
//
//            if (numberPlayer<8){
//                System.out.println("You can not play with this number of players\n pleas try again");
//                return true;
//            }else
//            {
//                playerSet.add(new Player(new doctor()));
//                playerSet.add(new Player(new Mayor()));
//                playerSet.add(new Player(new Detective()));
//                playerSet.add(new Player(new Die_hard()));
//                playerSet.add(new Player(new GodFather()));
//                playerSet.add(new Player(new Dr_Lecter()));
//                playerSet.add(new Player(new professional()));
//                playerSet.add(new Player(new Psychologist()));
//                numberPlayer-=8;
//                for (int t =0; t<(int) Math.ceil(numberPlayer/3)+1; t++)
//                {
//                    playerSet.add(new Player(new SimpleMafia()));
//                }
//                for (int t =0; t<numberPlayer-(int) Math.ceil(numberPlayer/3)-1 ; t++)
//                {
//                    playerSet.add(new Player(new Citizen()));
//                }
//              return false;
//            }
//
//    }
//    public void s()
//    {
//        for (Player p:
//            playerSet ) {
//            System.out.println(p.toString());
//            System.out.println(playerSet.size());
//
//        }
//    }
//    public Player getplayer() {
//
//        Random rand = new Random();
//        int tmp = rand.nextInt(playerSet.size());
//        return playerSet.get(tmp);
//    }
//    public boolean checkname(String n)
//    {
//        boolean  exist=false;
//        for (Player p:playerSet) {
//            if (p.getNamePlayer().equals(n)){ exist=true;}
//        }
//        return  exist;
//    }
//
//}
