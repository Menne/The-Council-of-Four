package actionsTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.actions.standardActions.EngageAssistant;
import server.model.stateMachine.State11;

public class EngageAssistantTest {

	@Test
	public void testExecueAction() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player();
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.setState(new State11());
		EngageAssistant action= new EngageAssistant();
		int oldNumberOfAssistants= game.getCurrentPlayer().getNumberOfAssistants();
		int oldCoins= game.getCurrentPlayer().getCoins();
		assertTrue(action.executeAction(game));
		assertEquals(oldNumberOfAssistants+1, game.getCurrentPlayer().getNumberOfAssistants());
		assertEquals(oldCoins-3, game.getCurrentPlayer().getCoins());
	}

	@Test
	public void testIfExecuteReturnsFalse() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player();
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.setState(new State11());
		EngageAssistant action= new EngageAssistant();
		game.getCurrentPlayer().decrementCoins(8);
		assertFalse(action.executeAction(game));
	}
}
