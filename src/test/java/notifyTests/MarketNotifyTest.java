package notifyTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import clientUpdates.MarketUpdate;
import server.model.Game;
import server.model.player.Player;
import server.serverNotifies.MarketServerNotify;

public class MarketNotifyTest {

	@Test
	public void test() throws IOException {
		Game game= new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		List<Player> interestedPlayers= new ArrayList<>();
		boolean startMarket= true;
		boolean closeMarket= false;
		MarketServerNotify notify= new MarketServerNotify(game, interestedPlayers, startMarket, closeMarket);
		assertEquals(MarketUpdate.class,notify.toClientNotify().getClass());
		assertTrue(notify.sendTo()==interestedPlayers);
	}

}
