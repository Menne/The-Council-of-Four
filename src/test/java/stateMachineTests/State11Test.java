package stateMachineTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.actions.standardActions.AcquirePermitTile;
import server.model.actions.standardActions.BuildByKing;
import server.model.actions.standardActions.BuildByPermitTile;
import server.model.actions.standardActions.ChangePermitTiles;
import server.model.actions.standardActions.ElectCouncillor;
import server.model.actions.standardActions.ElectCouncillorByAssistant;
import server.model.actions.standardActions.EngageAssistant;
import server.model.player.Player;
import server.model.stateMachine.BeginState;
import server.model.stateMachine.SellingState;
import server.model.stateMachine.State01;
import server.model.stateMachine.State10;
import server.model.stateMachine.State11;

public class State11Test {

	@Test
	public void testMAinActionTransition() throws IOException {
		Game game=new Game();
		State11 state= new State11();
		assertEquals(State01.class, state.mainActionTransition(game).getClass());
	}

	@Test
	public void testQuickActionTransition() throws IOException {
		Game game= new Game();
		State11 state= new State11();
		assertEquals(State10.class, state.quickActionTransition(game).getClass());
	}
	
	@Test
	public void testMoveToNextTransition() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		Player b = new Player("Andre");
		b.setPlayerNumber(2);
		players.add(a);
		players.add(b);
		game.start(players);
		game.setCurrentPlayer(a);
		State11 state= new State11();
		assertTrue(BeginState.class==state.moveToNextTransition(game).getClass());
		assertTrue(SellingState.class==state.moveToNextTransition(game).getClass());
	}
	
	@Test
	public void testGetAcceptableActions(){
		Game game= new Game();
		State11 state= new State11();
		assertEquals(ElectCouncillor.class, state.getAcceptableActions(game).get(0).getClass());
		assertEquals(AcquirePermitTile.class, state.getAcceptableActions(game).get(1).getClass());
		assertEquals(BuildByPermitTile.class, state.getAcceptableActions(game).get(2).getClass());
		assertEquals(BuildByKing.class, state.getAcceptableActions(game).get(3).getClass());
		assertEquals(EngageAssistant.class, state.getAcceptableActions(game).get(4).getClass());
		assertEquals(ChangePermitTiles.class, state.getAcceptableActions(game).get(5).getClass());
		assertEquals(ElectCouncillorByAssistant.class, state.getAcceptableActions(game).get(6).getClass());
	}
}
