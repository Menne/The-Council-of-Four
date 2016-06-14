package bonusTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
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
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(game.getPlayers().get(0));
		int temp=game.getCurrentPlayer().getHand().size();
		PoliticsCardsBonus bonus= new PoliticsCardsBonus(3);
		PoliticsCardsBonus bonus1= new PoliticsCardsBonus(3);
		bonus.assignBonus(game);
		assertEquals(temp+3, game.getCurrentPlayer().getHand().size());
		assertEquals("PoliticsCards+" + 3, bonus.toString());
		assertEquals(34, bonus.hashCode());
		assertEquals(bonus, bonus1);
	}
}