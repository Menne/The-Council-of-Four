package notifyTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.clientNotifies.ClientPlayerDTONotify;
import server.model.Game;
import server.model.player.Player;
import server.view.notifies.PlayerNotify;

public class PlayerNotifyTest {

	@Test
	public void test() {
		Game game= new Game();
		List<Player> interestedPlayers= new ArrayList<>();
		Player player= new Player("a");
		interestedPlayers.add(player);
		PlayerNotify notify= new PlayerNotify(game, player, interestedPlayers);
		assertEquals(ClientPlayerDTONotify.class, notify.toClientNotify().getClass());
		assertTrue(interestedPlayers==notify.sendTo());	
	}

}
