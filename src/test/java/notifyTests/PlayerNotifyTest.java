package notifyTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import clientUpdates.PlayerUpdate;
import server.model.Game;
import server.model.player.Player;
import server.serverNotifies.PlayerServerNotify;

public class PlayerNotifyTest {

	@Test
	public void test() {
		Game game= new Game();
		List<Player> interestedPlayers= new ArrayList<>();
		Player player= new Player("a");
		interestedPlayers.add(player);
		PlayerServerNotify notify= new PlayerServerNotify(game, player, interestedPlayers);
		assertEquals(PlayerUpdate.class, notify.toClientNotify().getClass());
		assertTrue(interestedPlayers==notify.sendTo());	
	}

}
