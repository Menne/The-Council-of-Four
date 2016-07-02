package stateMachineTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.actions.Action;
import server.model.player.Player;
import server.model.stateMachine.State11;
import server.model.stateMachine.bonusStates.InteractiveBonusState;

public class InteractiveBonusStateTest {

	@Test
	public void test() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		State11 previousState= new State11();
		game.setState(previousState);
		InteractiveBonusState state= new InteractiveBonusState(previousState);
		assertTrue(state== state.mainActionTransition(game));
		assertEquals(previousState.mainActionTransition(game).getClass(),
				state.moveToNextTransition(game).getClass());
		List<Action> actions= new ArrayList<>();
		assertEquals(actions, state.getAcceptableActions(game));
	}

}
