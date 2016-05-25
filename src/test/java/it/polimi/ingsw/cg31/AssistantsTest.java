package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import model.Game;
import model.gameTable.Assistant;

public class AssistantsTest {

	@Test
	public void test() throws IOException {
		Game game=new Game();
		game.addPlayer("Luca");
		Assistant a=new Assistant();
		int i=game.getPlayers().get(0).getNumberOfAssistants();
		a.addObjectToPlayer(game.getPlayers().get(0));
		assertEquals(i+1, game.getPlayers().get(0).getNumberOfAssistants());
	}

	@Test
	public void testRemoveFromPlayer() throws IOException {
		Game game=new Game();
		game.addPlayer("Luca");
		Assistant a=new Assistant();
		int i=game.getPlayers().get(0).getNumberOfAssistants();
		a.addObjectToPlayer(game.getPlayers().get(0));
		a.removeObjectFromPlayer(game.getPlayers().get(0));
		assertEquals(i, game.getPlayers().get(0).getNumberOfAssistants());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExceptionOfRemove() throws IOException{
		Game game=new Game();
		game.addPlayer("Luca");
			while(game.getPlayers().get(0).getNumberOfAssistants()!=0)
				game.getPlayers().get(0).decrementAssistants(1);
		Assistant a= new Assistant();
		a.removeObjectFromPlayer(game.getPlayers().get(0)); //deve lanciare un'eccezione!!
	}
}
