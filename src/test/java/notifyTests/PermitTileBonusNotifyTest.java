package notifyTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.clientNotifies.PermitTileBonusDTONotify;
import server.model.player.Player;
import server.view.notifies.PermitTileBonusNotify;

public class PermitTileBonusNotifyTest {

	@Test
	public void test() {
		List<Player> interestedPlayers= new ArrayList<>();
		Player a= new Player("a");
		interestedPlayers.add(a);
		PermitTileBonusNotify notify= new PermitTileBonusNotify(interestedPlayers);
		assertEquals(PermitTileBonusDTONotify.class, notify.toClientNotify().getClass());
		assertTrue(interestedPlayers==notify.sendTo());
	}

}
