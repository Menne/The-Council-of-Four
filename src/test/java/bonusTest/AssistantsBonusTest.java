package bonusTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.bonus.AssistantsBonus;

public class AssistantsBonusTest {

	@Test
	public void test() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(game.getPlayers().get(0));
		AssistantsBonus bonus= new AssistantsBonus(1);
		AssistantsBonus bonus1= new AssistantsBonus(1);
		int temp=game.getPlayers().get(0).getNumberOfAssistants();
		bonus.assignBonus(game);
		assertEquals(temp+1, game.getPlayers().get(0).getNumberOfAssistants());
		assertEquals("assistants+" + 1, bonus.toString());
		assertEquals(32, bonus.hashCode());
		assertEquals(bonus, bonus1);
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testException() throws IOException{
		new AssistantsBonus(0);
	}

	@Test(expected= IllegalArgumentException.class)
	public void testNegativeException() throws IOException{
		new AssistantsBonus(-3);
	}

}