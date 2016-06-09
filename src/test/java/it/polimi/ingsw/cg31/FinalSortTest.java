package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import players.Player;
import server.model.Game;

public class FinalSortTest {
	
	@Test
	public void ifFinalSortWork(){
		Player a =new Player("a");
		Player b =new Player("b");
		Player c =new Player("c");
		
		a.setPlayerNumber(1);
		b.setPlayerNumber(3);
		c.setPlayerNumber(2);
		
		a.setScore(1);
		b.setScore(3);
		c.setScore(4);
		
		Game game= new Game();
		game.getQuittedPlayers().addAll(Arrays.asList(a,b,c));
		game.sortFinalRankingTable();
		
		assertEquals(game.getQuittedPlayers().get(0), a);
		assertEquals(game.getQuittedPlayers().get(1), b);
		assertEquals(game.getQuittedPlayers().get(2), c);
		
	}

}
