package notifyTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import clientUpdates.ChatMessageUpdate;
import server.model.player.Player;
import server.serverNotifies.ChatServerNotify;

public class ChatNotifyTest {

	@Test
	public void test() {
		List<Player> interestedPlayers= new ArrayList<>();
		String message= "message";
		Player a= new Player("Andre");
		interestedPlayers.add(a);
		ChatServerNotify notify= new ChatServerNotify(message, interestedPlayers);
		assertEquals(ChatMessageUpdate.class, notify.toClientNotify().getClass());
		assertTrue(interestedPlayers==notify.sendTo());
	}

}
