package ActonsTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.actions.standardActions.AdditionalMainAction;
import server.model.stateMachine.State01;
import server.model.stateMachine.State10;

public class AddictionalMainActionTerst {

	@Test
	public void test() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<Player>();
		Player a = new Player();
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.getCurrentPlayer().incrementAssistants(2);
		game.setState(new State01());
		assertEquals(3, game.getCurrentPlayer().getNumberOfAssistants());
		AdditionalMainAction action= new AdditionalMainAction();
		assertTrue(action.executeAction(game));
		assertEquals(0, game.getCurrentPlayer().getNumberOfAssistants());
		State10 statofinale= new State10();
		assertEquals(statofinale.getClass(), game.getState().getClass());
	}

	
	@Test
	public void testWithTooFewAssistants() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<Player>();
		Player a = new Player();
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.setState(new State01());
		AdditionalMainAction action= new AdditionalMainAction();
		assertFalse(action.executeAction(game));
	}
	
}
