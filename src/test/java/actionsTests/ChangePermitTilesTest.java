package actionsTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.actions.standardActions.ChangePermitTiles;
import server.model.gameTable.PermitTile;
import server.model.stateMachine.State10;
import server.model.stateMachine.State11;

public class ChangePermitTilesTest {

	@Test
	public void testExecuteAction() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		Set<PermitTile> oldTiles= new HashSet<>();
		oldTiles.add(game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0]);
		oldTiles.add(game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[1]);
		Set<PermitTile> newTiles= new HashSet<>();
		ChangePermitTiles action= new ChangePermitTiles();
		action.setSelectedRegion(game.getGameTable().getRegionBoards().get(0));
		game.setState(new State11());
		action.executeAction(game);
		newTiles.add(game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0]);
		newTiles.add(game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[1]);
		assertNotEquals(oldTiles, newTiles);
		assertEquals(0, game.getCurrentPlayer().getNumberOfAssistants());
		assertEquals(State10.class, game.getState().getClass());
	}

	@Test(expected=NullPointerException.class)
	public void testException() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		ChangePermitTiles action= new ChangePermitTiles();
		game.setState(new State11());
		action.executeAction(game);
	}
	
	@Test
	public void testBooleanFalse() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.getCurrentPlayer().decrementAssistants(1);
		assertEquals(0, game.getCurrentPlayer().getNumberOfAssistants());
		ChangePermitTiles action= new ChangePermitTiles();
		action.setSelectedRegion(game.getGameTable().getRegionBoards().get(0));
		game.setState(new State11());
		assertFalse(action.executeAction(game));
	}
}
