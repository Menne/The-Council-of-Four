package actionsTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.actions.ChatMessage;
import server.model.player.Player;

public class ChatMessageTest {

	@Test
	public void test() throws IOException {
		Game game= new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		String message= "etc";
		ChatMessage action= new ChatMessage(message);
		assertTrue(action.executeAction(game));
	}
	
	@Test(expected= IllegalStateException.class)
	public void testException() {
		String message= "etc";
		ChatMessage action= new ChatMessage(message);
		action.map();
		assertFalse(true);
	}

}
