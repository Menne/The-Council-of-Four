package notifyTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.clientNotifies.EndGameDTONotifies;
import server.model.Game;
import server.model.player.Player;
import server.view.notifies.EndGameNotify;

public class EndGameNotifyTest {

	@Test
	public void test() {
		List<Player> finalRankingTable= new ArrayList<>();
		Player a= new Player("Andre");
		finalRankingTable.add(a);
		Game game= new Game();
		EndGameNotify notify= new EndGameNotify(game, finalRankingTable);
		assertEquals(EndGameDTONotifies.class, notify.toClientNotify().getClass());
		assertTrue(notify.sendTo()==finalRankingTable);
	}

}
