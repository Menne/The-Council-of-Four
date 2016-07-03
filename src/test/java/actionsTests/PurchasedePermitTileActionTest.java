package actionsTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import client.modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;
import server.model.Game;
import server.model.actions.bonusActions.PurchasedPermitTileAction;
import server.model.bonuses.Bonus;
import server.model.bonuses.ScoreBonus;
import server.model.gameTable.PermitTile;
import server.model.player.Player;
import server.model.stateMachine.State01;
import server.model.stateMachine.State11;
import server.model.stateMachine.bonusStates.InteractiveBonusState;

public class PurchasedePermitTileActionTest {

	@Test
	public void testExecuteAction() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		PurchasedPermitTileAction action= new PurchasedPermitTileAction();
		Set<Bonus> bonuses= new HashSet<>();
		bonuses.add(new ScoreBonus(1));
		PermitTile tile= new PermitTile(null, bonuses, game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck());
		action.setSelectedPermitTile(tile);
		a.addTile(tile);
		game.setCurrentPlayer(a);
		game.setState(new InteractiveBonusState(new State11()));
		assertTrue(a.getScore()==0);
		assertTrue(action.executeAction(game));
		assertTrue(a.getScore()==1);
		assertEquals(State01.class, game.getState().getClass());
	}
	

	@Test
	public void testIllegalException() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		PurchasedPermitTileAction action= new PurchasedPermitTileAction();
		assertEquals(PurchasedPermitTileActionDTO.class, action.map().getClass());
	}
}
