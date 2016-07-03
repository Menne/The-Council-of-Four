package notifyTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import clientUpdates.GameTableUpdate;
import server.model.Game;
import server.model.player.Player;
import server.serverNotifies.GameTableServerNotify;

public class GameTableNotifyTest {

	@Test
	public void test() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		List<Player> interestedPlayers= new ArrayList<>();
		interestedPlayers.add(a);
		game.setCurrentPlayer(a);
		GameTableServerNotify notify= new GameTableServerNotify(game, interestedPlayers,false);
		assertEquals(notify.toClientNotify().getClass(), GameTableUpdate.class);
		assertTrue(interestedPlayers==notify.sendTo());
		assertTrue(a==notify.getCurrentPlayer());
	}

}
