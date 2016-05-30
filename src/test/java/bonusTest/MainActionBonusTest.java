package bonusTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.bonus.MainActionBonus;

public class MainActionBonusTest {

	@Test
	public void mainActionBonusTest() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player();
		players.add(a);
		game.start(players);
		MainActionBonus bonus= new MainActionBonus();
		bonus.assignBonus(game);
		assertTrue(game.isAdditionalMainActionBonus());
	}

}
