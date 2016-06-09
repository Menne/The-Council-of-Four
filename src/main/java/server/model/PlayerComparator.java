package server.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import players.Player;

public class PlayerComparator implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		if(o1.getPlayerNumber()==o2.getPlayerNumber())
			return 0;
		
		if(o1.getScore()<o2.getScore())
			return 1;
		else if(o1.getScore()>o2.getScore())
			return -1;
		else if(o1.getNumberOfAssistants()+o1.getHand().size()<o2.getNumberOfAssistants()+o2.getHand().size())
			return -1;
		else if(o1.getNumberOfAssistants()+o1.getHand().size()<o2.getNumberOfAssistants()+o2.getHand().size())
			return 1;
		else
			return o1.getPlayerNumber()<o2.getPlayerNumber() ? -1 : 1;
		
	}

	public static void main(String[] args) {
		List<Player> players =new ArrayList<>();
		players.addAll(Arrays.asList(new Player("a"),new Player("b"),new Player("c")));
		players.get(0).setPlayerNumber(1);
		players.get(1).setPlayerNumber(2);
		players.get(2).setPlayerNumber(3);
		players.get(0).setScore(1);
		players.get(1).setScore(0);
		players.get(2).setScore(10);
		Collections.sort(players, new PlayerComparator());
		System.out.println(players);
	}
}
