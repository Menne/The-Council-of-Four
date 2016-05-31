package actionsTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.actions.standardActions.ElectCouncillor;
import server.model.gameTable.Councillor;
import server.model.stateMachine.State01;
import server.model.stateMachine.State11;

public class ElectCouncillorTest {

	@Test
	public void testExecuteAction() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player();
		
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		Councillor[] oldBalcony=game.getGameTable().getRegionBoards().get(0).getRegionBalcony().getCouncillors();
		game.setState(new State11());
		ElectCouncillor action=new ElectCouncillor();
		action.setCouncilBalcony(game.getGameTable().getRegionBoards().get(0).getRegionBalcony());
		Councillor councillor=game.getGameTable().getCouncilReserve().getCouncillors().get(0);
		action.setNewCouncillor(councillor);
		assertTrue(action.executeAction(game));
		Councillor[] newBalcony=game.getGameTable().getRegionBoards().get(0).getRegionBalcony().getCouncillors();
		assertEquals(14, game.getCurrentPlayer().getCoins());
		assertTrue(councillor==game.getGameTable().getRegionBoards().get(0).getRegionBalcony().getCouncillors()[0]);
		assertTrue(oldBalcony[0]== newBalcony[1]);
		assertTrue(oldBalcony[1]== newBalcony[2]);
		assertTrue(oldBalcony[2]== newBalcony[3]);
		assertEquals(State01.class, game.getState().getClass());
	}
	
	@Test(expected=NullPointerException.class)
	public void testExceptionForCouncillorNotSelected() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player();
		
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.setState(new State11());
		ElectCouncillor action=new ElectCouncillor();
		action.setCouncilBalcony(game.getGameTable().getRegionBoards().get(0).getRegionBalcony());
		action.executeAction(game);
	}

	@Test(expected=NullPointerException.class)
	public void testExceptionForCouncilBalconyNotSelected() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player();
		
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.setState(new State11());
		ElectCouncillor action=new ElectCouncillor();
		Councillor councillor=game.getGameTable().getCouncilReserve().getCouncillors().get(0);
		action.setNewCouncillor(councillor);
		action.executeAction(game);
	}
}
