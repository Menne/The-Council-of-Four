package actionsTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.actions.standardActions.ElectCouncillorByAssistant;
import server.model.gameTable.Councillor;
import server.model.stateMachine.State10;
import server.model.stateMachine.State11;

public class ElectCouncillorByAssistantTest {

	@Test
	public void testExecuteAction() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		Councillor[] oldBalcony=game.getGameTable().getRegionBoards().get(0).getRegionBalcony().getCouncillors();
		game.setState(new State11());
		ElectCouncillorByAssistant action=new ElectCouncillorByAssistant();
		action.setCouncilBalcony(game.getGameTable().getRegionBoards().get(0).getRegionBalcony());
		Councillor councillor=game.getGameTable().getCouncilReserve().getCouncillors().get(0);
		action.setNewCouncillor(councillor);
		int oldNumberOfAssistants= game.getCurrentPlayer().getNumberOfAssistants();
		assertTrue(action.executeAction(game));
		Councillor[] newBalcony=game.getGameTable().getRegionBoards().get(0).getRegionBalcony().getCouncillors();
		assertEquals(oldNumberOfAssistants-1, game.getCurrentPlayer().getNumberOfAssistants());
		assertTrue(councillor==newBalcony[0]);
		assertTrue(oldBalcony[0]==newBalcony[1]);
		assertTrue(oldBalcony[1]==newBalcony[2]);
		assertTrue(oldBalcony[2]==newBalcony[3]);
		assertTrue(game.getGameTable().getCouncilReserve().getCouncillors().contains(oldBalcony[3]));
		assertEquals(State10.class, game.getState().getClass());
	}
	
	@Test(expected=NullPointerException.class)
	public void testExceptionIfBalconyIsNull() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.setState(new State11());
		ElectCouncillorByAssistant action=new ElectCouncillorByAssistant();
		Councillor councillor=game.getGameTable().getCouncilReserve().getCouncillors().get(0);
		action.setNewCouncillor(councillor);
		action.executeAction(game);
	}
	
	@Test(expected=NullPointerException.class)
	public void testExceptionIfCouncillorIsNull() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.setState(new State11());
		ElectCouncillorByAssistant action=new ElectCouncillorByAssistant();
		action.setCouncilBalcony(game.getGameTable().getRegionBoards().get(0).getRegionBalcony());
		action.executeAction(game);
	}
	
	@Test
	public void testIfReturnsFalseInCasePlayerHasTooFewAssistants() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.setState(new State11());
		game.getCurrentPlayer().decrementAssistants(1);
		assertTrue(game.getCurrentPlayer().getNumberOfAssistants()==0);
		ElectCouncillorByAssistant action=new ElectCouncillorByAssistant();
		action.setCouncilBalcony(game.getGameTable().getRegionBoards().get(0).getRegionBalcony());
		Councillor councillor=game.getGameTable().getCouncilReserve().getCouncillors().get(0);
		action.setNewCouncillor(councillor);
		assertFalse(action.executeAction(game));
		
	}
}
