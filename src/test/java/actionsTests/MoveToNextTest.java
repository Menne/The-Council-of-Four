package actionsTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.actionsDTO.MoveToNextDTO;
import server.model.Game;
import server.model.actions.MoveToNext;
import server.model.player.Player;
import server.model.stateMachine.SellingState;
import server.model.stateMachine.State01;

public class MoveToNextTest {

	@Test
	public void test() {
		MoveToNext action= new MoveToNext();
		assertEquals(MoveToNextDTO.class,action.map().getClass());
	}
	
	@Test 
	public void testExecuteAction() throws IOException {
		Game game= new Game();
		MoveToNext action= new MoveToNext();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.setState(new State01());
		assertTrue(action.executeAction(game));
		assertEquals(SellingState.class, game.getState().getClass());
	}

}
