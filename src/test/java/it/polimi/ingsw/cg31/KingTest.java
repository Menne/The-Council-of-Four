
package it.polimi.ingsw.cg31;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.gameTable.City;
import server.model.gameTable.King;
import server.model.player.Player;


public class KingTest {

	@Test
	public void testIfJuvelarHasTheKing() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		City c=null;
		c=game.getGameTable().getKing().getCity();
		assertEquals("Juvelar", c.getName());
	}
	
	
	@Test
	public void testKingGetter() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		City c=null;
		King king= new King(c);
		assertEquals(c, king.getCity());
	}
}