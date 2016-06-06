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
import server.model.stateMachine.State01;
import server.model.stateMachine.State10;

public class State01Test {

	@Test
	public void testQuickActionTransition() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		Player b = new Player("Andre");
		b.setPlayerNumber(2);
		players.add(a);
		players.add(b);
		game.start(players);
		game.setCurrentPlayer(a);
		State01 state= new State01();
		game.setState(state);
		assertEquals(BeginState.class, state.quickActionTransition(game).getClass());
		assertEquals(b, game.getCurrentPlayer());
		game.setState(state);
		assertEquals(SellingState.class, state.quickActionTransition(game).getClass());
		assertEquals(a, game.getCurrentPlayer());
	}

	@Test
	public void testAddictionalMainActionTransition(){
		State01 state= new State01();
		assertEquals(State10.class, state.additionalMainActionTransition().getClass());
	}

	@Test
	public void testMoveToNextTransition() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		Player b = new Player("Andre");
		b.setPlayerNumber(2);
		players.add(a);
		players.add(b);
		game.start(players);
		game.setCurrentPlayer(a);
		State01 state= new State01();
		game.setState(state);
		assertEquals(BeginState.class, state.moveToNextTransition(game).getClass());
		assertEquals(b, game.getCurrentPlayer());
		game.setState(state);
		assertEquals(SellingState.class, state.moveToNextTransition(game).getClass());
		assertEquals(a, game.getCurrentPlayer());
	}
}

