package actionsTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.actions.Quit;
import server.model.player.Player;
import server.model.stateMachine.BeginState;

public class QuitTest {

	@Test
	public void testExecuteAction() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		Player b = new Player("Andre");
		Player c = new Player("Andre");
		Player d = new Player("Andre");
		a.setPlayerNumber(1);
		b.setPlayerNumber(2);
		c.setPlayerNumber(3);
		d.setPlayerNumber(4);
		players.add(a);
		players.add(b);
		players.add(c);
		players.add(d);
		game.start(players);
		BeginState state= new BeginState();
		game.setState(state);
		game.setCurrentPlayer(a);
		Quit action= new Quit(a);
		assertTrue(action.executeAction(game));
		assertTrue(state.moveToNextTransition(game).getClass()==game.getState().getClass());
		assertTrue(game.getPlayers().size()==3);
		assertTrue(game.getQuittedPlayers().size()==1);
		assertTrue(game.getQuittedPlayers().contains(a));
		assertTrue(!game.getPlayers().contains(a));
		assertFalse(game.getMarket().getBuyingPlayerList().contains(a));
		assertFalse(game.getMarket().getSellingPlayerList().contains(a));
		game.setCurrentPlayer(d);
		Quit action1= new Quit(d);
		assertTrue(action1.executeAction(game));
		assertFalse(game.getPlayers().contains(d));
		assertTrue(game.getQuittedPlayers().contains(d));
		assertFalse(game.getMarket().getBuyingPlayerList().contains(d));
		assertFalse(game.getMarket().getSellingPlayerList().contains(d));
		game.setCurrentPlayer(b);
		Quit action2= new Quit(c);
		assertTrue(action2.executeAction(game));
		assertFalse(game.getPlayers().contains(c));
		assertFalse(game.getMarket().getBuyingPlayerList().contains(c));
		assertFalse(game.getMarket().getSellingPlayerList().contains(c));
		assertTrue(game.getQuittedPlayers().contains(c));
		assertTrue(game.getQuittedPlayers().contains(b));
	}

	@Test(expected= IllegalStateException.class)
	public void testException(){
		Player a = new Player("a");
		Quit action= new Quit(a);
		action.map();
	}
}
