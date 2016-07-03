package notifyTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import clientUpdates.ErrorUpdate;
import server.model.player.Player;
import server.serverNotifies.ErrorServerNotify;

public class ErrorNotifyTest {

	@Test
	public void test() {
		String message="message";
		List<Player> interestedPlayers=new ArrayList<>();
		Player a= new Player("Andre");
		interestedPlayers.add(a);
		ErrorServerNotify notify= new ErrorServerNotify(message, interestedPlayers);
		assertEquals(ErrorUpdate.class, notify.toClientNotify().getClass());
		assertTrue(notify.sendTo()==interestedPlayers);
	}

}
