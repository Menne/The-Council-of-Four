package stateMachineTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.stateMachine.BeginState;
import server.model.stateMachine.SellingState;
import server.model.stateMachine.State10;

public class State10Test {

	@Test
	public void testMainActionTransition() throws IOException {
			Game game=new Game();
			List<Player> players = new ArrayList<>();
			Player a = new Player("Andre");
			Player b = new Player("Andre");
			b.setPlayerNumber(2);
			players.add(a);
			players.add(b);
			game.start(players);
			State10 state= new State10();
			game.setCurrentPlayer(a);
			game.setState(state);
			assertEquals(BeginState.class, state.mainActionTransition(game).getClass());
			assertEquals(b, game.getCurrentPlayer());
			game.setState(state);
			assertEquals(SellingState.class, state.mainActionTransition(game).getClass());
			assertEquals(a, game.getCurrentPlayer());
	}
}
