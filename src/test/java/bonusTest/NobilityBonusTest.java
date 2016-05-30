package bonusTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.bonus.NobilityBonus;


public class NobilityBonusTest {

	@Test
	public void testAssignBonus() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<Player>();
		Player a = new Player();
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(game.getPlayers().get(0));
		NobilityBonus bonus= new NobilityBonus(2);
		int temp=game.getCurrentPlayer().getScore();
		bonus.assignBonus(game);
		assertEquals(temp+2, game.getCurrentPlayer().getScore());
		assertEquals(2, game.getCurrentPlayer().getNobility());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeException(){
		new NobilityBonus(-2);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testZeroException(){
		new NobilityBonus(0);
	}
	
}
