package BonusTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import model.bonus.ScoreBonus;
import players.Player;
import server.model.Game;

public class ScoreBonusTest {

	@Test
	public void testAssignBonus() throws IOException {
		Game game=new Game();
		game.addPlayer("Andrea");
		game.nextPlayer();
		ScoreBonus bonus=new ScoreBonus(1);
		bonus.assignBonus(game);
		assertEquals(1, game.getCurrentPlayer().getScore());
	}
	
	@Test
	public void testAssignBonusToPlayer() throws IOException {
		Game game=new Game();
		game.addPlayer("Andrea");
		ScoreBonus bonus=new ScoreBonus(1);
		bonus.assignBonusToPlayer(game.getPlayers().get(0));
		assertEquals(1, game.getPlayers().get(0).getScore());
	}

}
