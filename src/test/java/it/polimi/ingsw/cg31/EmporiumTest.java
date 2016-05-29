package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.gameTable.Emporium;



public class EmporiumTest {

	
	@Test
	public void testIfEmporiumsGetterReturnsTheRightPlayer() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<Player>();
		Player a = new Player();
		players.add(a);
		game.start(players);
		Emporium e= new Emporium(game.getPlayers().get(0));
		
		assertEquals(game.getPlayers().get(0), e.getEmporiumsPlayer());
		
	}

}
