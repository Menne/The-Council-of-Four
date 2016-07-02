package stateMachineTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.actions.Action;
import server.model.player.Player;
import server.model.stateMachine.State01;
import server.model.stateMachine.bonusStates.AdditionalMainActionBonusState;

public class AdditionalMainActionBonusStateTest {

	@Test
	public void testMainActionTransition() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		State01 previousState= new State01();
		game.setState(previousState);
		AdditionalMainActionBonusState state= new AdditionalMainActionBonusState(previousState);
		assertTrue(state.mainActionTransition(game)==previousState);
		List<Action> actions= new ArrayList<>();
		assertEquals(actions, state.getAcceptableActions(game));
		
	}
	
	@Test(expected=IllegalStateException.class)
	public void testException() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		State01 previousState= new State01();
		game.setState(previousState);
		AdditionalMainActionBonusState state= new AdditionalMainActionBonusState(previousState);
		state.moveToNextTransition(game);
	}

}
