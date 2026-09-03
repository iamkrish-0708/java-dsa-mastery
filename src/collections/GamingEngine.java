package collections;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class GamingEngine {
    public static void main(String[] args) {
        Set<String> uniqueEmails=new HashSet<>();
        boolean u1=uniqueEmails.add("player1@ex.com");
        boolean u2=uniqueEmails.add("player2@ex.com");
        boolean u3=uniqueEmails.add("player1@ex.com");
        boolean u4=uniqueEmails.add("player3@ex.com");

        System.out.println("is player1@ex.com added in second attempt ->"+u3);
        System.out.println(uniqueEmails +"\nTotal Size="+uniqueEmails.size());

        TreeSet<Integer> leaderboard=new TreeSet<>();
        leaderboard.add(450);
        leaderboard.add(1200);
        leaderboard.add(850);
        leaderboard.add(1200);
        leaderboard.add(300);
        leaderboard.add(1500);
        System.out.println(leaderboard);
        System.out.println("Highest Score="+leaderboard.last()+"\nLowest Score="+leaderboard.first());
        System.out.println("Scores >=850:"+leaderboard.tailSet(850));


    }
}
