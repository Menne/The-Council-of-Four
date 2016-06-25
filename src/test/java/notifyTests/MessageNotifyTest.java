package notifyTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.clientNotifies.MessageDTONotify;
import server.model.player.Player;
import server.view.notifies.MessageNotify;

public class MessageNotifyTest {

	@Test
	public void test() {
		String message="message";
		List<Player> interestedPlayers= new ArrayList<>();
		Player a= new Player("a");
		interestedPlayers.add(a);
		MessageNotify notify= new MessageNotify(message, interestedPlayers);
		assertEquals(MessageDTONotify.class, notify.toClientNotify().getClass());
		assertTrue(notify.sendTo()==interestedPlayers);
	}

}
