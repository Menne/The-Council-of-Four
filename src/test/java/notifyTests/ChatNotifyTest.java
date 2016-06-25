package notifyTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.clientNotifies.ChatMessageDTONotify;
import server.model.player.Player;
import server.view.notifies.ChatNotify;

public class ChatNotifyTest {

	@Test
	public void test() {
		List<Player> interestedPlayers= new ArrayList<>();
		String message= "message";
		Player a= new Player("Andre");
		interestedPlayers.add(a);
		ChatNotify notify= new ChatNotify(message, interestedPlayers);
		assertEquals(ChatMessageDTONotify.class, notify.toClientNotify().getClass());
		assertTrue(interestedPlayers==notify.sendTo());
	}

}
