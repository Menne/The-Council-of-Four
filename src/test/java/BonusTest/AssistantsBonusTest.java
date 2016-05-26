package BonusTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


import server.model.Game;
import server.model.bonus.AssistantsBonus;

public class AssistantsBonusTest {

	@Test
	public void test() throws IOException {
		Game game=new Game();
		game.addPlayer("Andrea");
		game.setCurrentPlayer(game.getPlayers().get(0));
		AssistantsBonus bonus= new AssistantsBonus(1);
		int temp=game.getPlayers().get(0).getNumberOfAssistants();
		bonus.assignBonus(game);
		assertEquals(temp+1, game.getPlayers().get(0).getNumberOfAssistants());
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testException() throws IOException{
		new AssistantsBonus(0);
	}

}
