package notifyTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import clientUpdates.EndGameUpdate;
import server.model.Game;
import server.model.player.Player;
import server.serverNotifies.EndGameServerNotify;

public class EndGameNotifyTest {

	@Test
	public void test() {
		List<Player> finalRankingTable= new ArrayList<>();
		Player a= new Player("Andre");
		finalRankingTable.add(a);
		Game game= new Game();
		EndGameServerNotify notify= new EndGameServerNotify(game, finalRankingTable);
		assertEquals(EndGameUpdate.class, notify.toClientNotify().getClass());
		assertTrue(notify.sendTo()==finalRankingTable);
	}

}
