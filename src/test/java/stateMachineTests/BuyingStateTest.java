package stateMachineTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.actions.Action;
import server.model.actions.MoveToNext;
import server.model.actions.marketActions.AcceptAnOffer;
import server.model.player.Player;
import server.model.stateMachine.BeginState;
import server.model.stateMachine.BuyingState;

public class BuyingStateTest {

	@Test
	public void testBuyActionTransition() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		game.setState(new BuyingState());
		BuyingState state= new BuyingState();
		List<Action> acceptableActions= Arrays.asList(new AcceptAnOffer(), new MoveToNext());
		assertEquals(state.buyActionTransition(game).getClass(), BeginState.class);
		assertEquals(state.buyActionTransition(game).getClass(), BeginState.class);
		assertEquals(state.getAcceptableActions(game).get(0).getClass(), acceptableActions.get(0).getClass());
		assertEquals(state.getAcceptableActions(game).get(1).getClass(), acceptableActions.get(1).getClass());
	}

	@Test
	public void testMoveToNextTransition() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		game.setState(new BuyingState());
		BuyingState state= new BuyingState();
		assertEquals(state.moveToNextTransition(game).getClass(), BeginState.class);
		assertEquals(state.moveToNextTransition(game).getClass(), BeginState.class);
	}
}
