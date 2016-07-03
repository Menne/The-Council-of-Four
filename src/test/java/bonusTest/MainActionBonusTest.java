package bonusTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.bonuses.MainActionBonus;
import server.model.player.Player;
import server.model.stateMachine.State10;
import server.model.stateMachine.State11;
import server.model.stateMachine.bonusStates.AdditionalMainActionBonusState;
public class MainActionBonusTest {

	@Test
	public void mainActionBonusTestFrom10() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		game.setState(new State10());
		MainActionBonus bonus= new MainActionBonus();
		bonus.assignBonus(game);
		assertEquals(AdditionalMainActionBonusState.class, game.getState().getClass());
		assertEquals("mainAction+1", bonus.toString());
	}

	@Test
	public void mainActionBonusTestFrom11() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		game.setState(new State11());
		MainActionBonus bonus= new MainActionBonus();
		bonus.assignBonus(game);
		assertEquals(AdditionalMainActionBonusState.class, game.getState().getClass());
	}
}