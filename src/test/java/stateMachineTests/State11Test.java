package stateMachineTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.stateMachine.State01;
import server.model.stateMachine.State10;
import server.model.stateMachine.State11;

public class State11Test {

	@Test
	public void testMAinActionTransition() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		State11 state= new State11();
		assertEquals(State01.class, state.mainActionTransition(game).getClass());
	}

	@Test
	public void testQuickActionTransition() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		State11 state= new State11();
		assertEquals(State10.class, state.quickActionTransition(game).getClass());
	}
}
