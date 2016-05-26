package BonusTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import server.model.Game;
import server.model.bonus.NobilityBonus;


public class NobilityBonusTest {

	@Test
	public void testAssignBonus() throws IOException {
		Game game= new Game();
		game.addPlayer("Andrea");
		game.setCurrentPlayer(game.getPlayers().get(0));
		NobilityBonus bonus= new NobilityBonus(2);
		int temp=game.getCurrentPlayer().getScore();
		bonus.assignBonus(game);
		assertEquals(temp+2, game.getCurrentPlayer().getScore());
		assertEquals(2, game.getCurrentPlayer().getNobility());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeException(){
		new NobilityBonus(-2);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testZeroException(){
		new NobilityBonus(0);
	}
	
}
