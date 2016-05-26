package BonusTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import server.model.Game;
import server.model.bonus.CoinsBonus;

public class CoinsBonusTest {

	@Test
	public void test() throws IOException {
		Game game= new Game();
		game.addPlayer("Andrea");
		game.setCurrentPlayer(game.getPlayers().get(0));
		int temp= game.getCurrentPlayer().getCoins();
		CoinsBonus bonus= new CoinsBonus(3);
		bonus.assignBonus(game);
		assertEquals(temp+3, game.getCurrentPlayer().getCoins());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testException(){
		new CoinsBonus(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeException(){
		new CoinsBonus(-2);
	}
}
