package notifyTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.clientNotifies.ErrorDTONotify;
import server.model.player.Player;
import server.view.notifies.ErrorNotify;

public class ErrorNotifyTest {

	@Test
	public void test() {
		String message="message";
		List<Player> interestedPlayers=new ArrayList<>();
		Player a= new Player("Andre");
		interestedPlayers.add(a);
		ErrorNotify notify= new ErrorNotify(message, interestedPlayers);
		assertEquals(ErrorDTONotify.class, notify.toClientNotify().getClass());
		assertTrue(notify.sendTo()==interestedPlayers);
	}

}
