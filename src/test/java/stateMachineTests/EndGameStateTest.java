package stateMachineTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.actions.Action;
import server.model.player.Player;
import server.model.stateMachine.EndGameState;

public class EndGameStateTest {

	@Test
	public void test() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player b = new Player("Andre");
		players.add(b);
		game.start(players);
		EndGameState state= new EndGameState();
		assertTrue(state.moveToNextTransition(game)== state);
		List<Action> actions= new ArrayList<>();
		assertEquals(actions, state.getAcceptableActions(game));
		state.updateAvailableActions(game);
	}

}
