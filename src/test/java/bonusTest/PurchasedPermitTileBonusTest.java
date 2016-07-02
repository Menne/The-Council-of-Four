package bonusTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.bonus.interactiveBonus.PurchasedPermitTileBonus;
import server.model.player.Player;
import server.model.stateMachine.State11;

public class PurchasedPermitTileBonusTest {

	@Test
	public void testIfPlayerHasNotPermitTilesTurnedUp() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		State11 state11= new State11();
		game.setState(state11);
		PurchasedPermitTileBonus bonus= new PurchasedPermitTileBonus();
		bonus.assignBonus(game);
		assertTrue(game.getState()==state11);
	}

	@Test
	public void testIfPlayerHasPermitTilesTurnedUp() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		State11 state11= new State11();
		game.setState(state11);
		PurchasedPermitTileBonus bonus= new PurchasedPermitTileBonus();
		a.getPlayersPermitTilesTurnedUp().add(game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0]);
		bonus.assignBonus(game);
		assertTrue(game.getState().getClass()==state11.interactiveBonusTransition().getClass());
	}
}
