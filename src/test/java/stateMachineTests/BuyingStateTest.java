package stateMachineTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.stateMachine.BeginState;
import server.model.stateMachine.BuyingState;

public class BuyingStateTest {

	@Test
	public void testBuyActionTransition() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player();
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		game.setState(new BuyingState());
		BuyingState state= new BuyingState();
		assertEquals(state.buyActionTransition(game).getClass(), BuyingState.class);
		assertEquals(state.buyActionTransition(game).getClass(), BeginState.class);
	}

	@Test
	public void testMoveToNextTransition() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player();
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		game.setState(new BuyingState());
		BuyingState state= new BuyingState();
		assertEquals(state.moveToNextTransition(game).getClass(), BuyingState.class);
		assertEquals(state.moveToNextTransition(game).getClass(), BeginState.class);
	}
}
