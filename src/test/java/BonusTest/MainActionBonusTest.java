package BonusTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import server.model.Game;
import server.model.bonus.MainActionBonus;

public class MainActionBonusTest {

	@Test
	public void mainActionBonusTest() throws IOException {
		Game game=new Game();
		MainActionBonus bonus= new MainActionBonus();
		bonus.assignBonus(game);
		assertTrue(game.isAdditionalMainActionBonus());
	}

}
