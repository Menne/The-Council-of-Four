package bonusTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.bonuses.interactiveBonus.ChooseCityBonus;
import server.model.player.Player;
import server.model.stateMachine.State11;

public class ChooseCityBonusTest {
		
	@Test
	public void testNormal() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		State11 state11= new State11();
		game.setState(state11);
		a.getRemainigEmporiums().remove(0);
		ChooseCityBonus bonus= new ChooseCityBonus(1);
		bonus.assignBonus(game);
		assertEquals(state11.interactiveBonusTransition().getClass(), game.getState().getClass());
	}
	
	@Test
	public void testIfPlayerHasNotBuildedEmporiums() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		State11 state11= new State11();	
		game.setState(state11);
		ChooseCityBonus bonus= new ChooseCityBonus(1);
		bonus.assignBonus(game);
		assertTrue(state11==game.getState());
	}
}
