package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import server.model.Game;
import server.model.gameTable.Emporium;



public class EmporiumTest {

	
	@Test
	public void testIfEmporiumsGetterReturnsTheRightPlayer() throws IOException {
		Game game=new Game();
		game.addPlayer("Luca");
		Emporium e= new Emporium(game.getPlayers().get(0));
		
		assertEquals(game.getPlayers().get(0), e.getEmporiumsPlayer());
		
	}

}
