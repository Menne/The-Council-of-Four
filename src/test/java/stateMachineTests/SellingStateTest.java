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
import server.model.actions.marketActions.MakeAnOffer;
import server.model.player.Player;
import server.model.stateMachine.BuyingState;
import server.model.stateMachine.SellingState;

public class SellingStateTest {

	@Test
	public void testSellActionTransition() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		SellingState state= new SellingState();
		game.setState(state);
		game.getMarket().getSellingPlayerList().add(a);
		game.getMarket().getBuyingPlayerList().add(a);
		assertTrue(state==state.sellActionTransition(game));
		assertEquals(BuyingState.class, state.sellActionTransition(game).getClass());
	}
	
	@Test
	public void testMoveToNextTransitionAndGetAcceptableActions() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		SellingState state= new SellingState();
		game.setState(state);
		game.getMarket().getSellingPlayerList().add(a);
		game.getMarket().getBuyingPlayerList().add(a);
		List<Action> acceptableActions= Arrays.asList(new MakeAnOffer(), new MoveToNext());
		assertEquals(state.getAcceptableActions(game).get(0).getClass(), acceptableActions.get(0).getClass());
		assertEquals(state.getAcceptableActions(game).get(1).getClass(), acceptableActions.get(1).getClass());
		assertTrue(state==state.moveToNextTransition(game));
		assertEquals(BuyingState.class, state.moveToNextTransition(game).getClass());
	}

}
