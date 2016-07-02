package actionsTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.actionsDTO.PickPoliticsCardDTO;
import server.model.Game;
import server.model.actions.PickPoliticsCard;
import server.model.player.Player;

public class PickPoliticCardTest {

	@Test
	public void test() throws IOException {
		Game game= new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		PickPoliticsCard action= new PickPoliticsCard();
		int oldNumberOfPoliticsCards= game.getCurrentPlayer().getHand().size();
		assertEquals(PickPoliticsCardDTO.class, action.map().getClass());
		assertEquals("pc: pick a politics card", action.toString());
		assertTrue(action.executeAction(game));
		assertTrue(game.getCurrentPlayer().getHand().size()==oldNumberOfPoliticsCards+1);
	}

}
