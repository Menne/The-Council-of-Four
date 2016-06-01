package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.gameTable.Assistant;

public class AssistantsTest {

	@Test
	public void testAddToPlayer() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player p = new Player();
		players.add(p);
		game.start(players);
		Assistant a=new Assistant();
		int i=game.getPlayers().get(0).getNumberOfAssistants();
		a.addObjectToPlayer(game.getPlayers().get(0));
		assertEquals(i+1, game.getPlayers().get(0).getNumberOfAssistants());
	}

	@Test
	public void testRemoveFromPlayer() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player p = new Player();
		players.add(p);
		game.start(players);
		Assistant a=new Assistant();
		int i=game.getPlayers().get(0).getNumberOfAssistants();
		a.addObjectToPlayer(game.getPlayers().get(0));
		a.removeObjectFromPlayer(game.getPlayers().get(0));
		assertEquals(i, game.getPlayers().get(0).getNumberOfAssistants());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExceptionOfRemove() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player p= new Player();
		players.add(p);
		game.start(players);
			while(game.getPlayers().get(0).getNumberOfAssistants()!=0)
				game.getPlayers().get(0).decrementAssistants(1);
		Assistant a= new Assistant();
		a.removeObjectFromPlayer(game.getPlayers().get(0)); //deve lanciare un'eccezione!!
	}
}
