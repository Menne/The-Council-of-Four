package notifyTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import clientUpdates.MessageUpdate;
import server.model.player.Player;
import server.serverNotifies.MessageServerNotify;

public class MessageNotifyTest {

	@Test
	public void test() {
		String message="message";
		List<Player> interestedPlayers= new ArrayList<>();
		Player a= new Player("a");
		interestedPlayers.add(a);
		MessageServerNotify notify= new MessageServerNotify(message, interestedPlayers);
		assertEquals(MessageUpdate.class, notify.toClientNotify().getClass());
		assertTrue(notify.sendTo()==interestedPlayers);
	}

}
