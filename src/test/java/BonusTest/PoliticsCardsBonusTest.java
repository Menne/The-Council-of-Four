package BonusTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import server.model.Game;
import server.model.bonus.PoliticsCardsBonus;

public class PoliticsCardsBonusTest {

	@Test(expected=IllegalArgumentException.class)
	public void testZeroException() {
		new PoliticsCardsBonus(0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNegativeException() {
		new PoliticsCardsBonus(-3);
	}
	
	@Test
	public void testAssignBonus() throws IOException {
		Game game=new Game();
		game.addPlayer("Andrea");
		game.setCurrentPlayer(game.getPlayers().get(0));
		int temp=game.getCurrentPlayer().getHand().size();
		PoliticsCardsBonus bonus= new PoliticsCardsBonus(3);
		bonus.assignBonus(game);
		assertEquals(temp+3, game.getCurrentPlayer().getHand().size());
	}
}
