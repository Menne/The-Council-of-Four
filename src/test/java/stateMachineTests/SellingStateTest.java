package stateMachineTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.stateMachine.BuyingState;
import server.model.stateMachine.SellingState;

public class SellingStateTest {

	/*@Test
	public void testSellActionTransition() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		game.setState(new SellingState());
		SellingState state= new SellingState();
		assertEquals(SellingState.class, state.sellActionTransition(game).getClass());
		SellingState state1= new SellingState();
		assertEquals(BuyingState.class, state1.sellActionTransition(game).getClass());
		
	}

	@Test
	public void testMoveToNextTransition() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		game.setState(new SellingState());
		SellingState state= new SellingState();
		assertEquals(SellingState.class, state.moveToNextTransition(game).getClass());
		SellingState state1= new SellingState();
		assertEquals(BuyingState.class, state1.moveToNextTransition(game).getClass());
	}*/
}
