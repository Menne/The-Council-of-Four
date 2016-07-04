package stateMachineTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.actions.MoveToNext;
import server.model.actions.standardActions.AdditionalMainAction;
import server.model.actions.standardActions.ChangePermitTiles;
import server.model.actions.standardActions.ElectCouncillorByAssistant;
import server.model.actions.standardActions.EngageAssistant;
import server.model.player.Player;
import server.model.stateMachine.BeginState;
import server.model.stateMachine.EndGameState;
import server.model.stateMachine.SellingState;
import server.model.stateMachine.State01;
import server.model.stateMachine.State10;

public class State01Test {

	@Test
	public void testQuickActionTransition() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		Player b = new Player("Andre");
		a.setPlayerNumber(1);
		b.setPlayerNumber(2);
		players.add(a);
		players.add(b);
		game.start(players);
		game.setCurrentPlayer(a);
		State01 state= new State01();
		game.setState(state);
		assertEquals(BeginState.class, state.quickActionTransition(game).getClass());
		assertEquals(b, game.getCurrentPlayer());
		game.setState(state);
		assertEquals(SellingState.class, state.quickActionTransition(game).getClass());
		assertEquals(a, game.getCurrentPlayer());
		game.setState(state);
		game.getPlayers().remove(1);
		assertEquals(EndGameState.class, state.quickActionTransition(game).getClass());
	}

	@Test
	public void testAddictionalMainActionTransition(){
		State01 state= new State01();
		assertEquals(State10.class, state.additionalMainActionTransition().getClass());
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
		State01 state= new State01();
		game.setState(state);
		assertEquals(BeginState.class, state.moveToNextTransition(game).getClass());
		assertEquals(b, game.getCurrentPlayer());
		game.setState(state);
		assertEquals(SellingState.class, state.moveToNextTransition(game).getClass());
		assertEquals(a, game.getCurrentPlayer());
		game.setState(state);
		game.getPlayers().remove(1);
		assertEquals(EndGameState.class, state.moveToNextTransition(game).getClass());
	}
	
	@Test
	public void testGetAcceptableActions(){
		Game game=new Game();
		State01 state= new State01();
		assertEquals(EngageAssistant.class, state.getAcceptableActions(game).get(0).getClass());
		assertEquals(ChangePermitTiles.class, state.getAcceptableActions(game).get(1).getClass());
		assertEquals(ElectCouncillorByAssistant.class, state.getAcceptableActions(game).get(2).getClass());
		assertEquals(AdditionalMainAction.class, state.getAcceptableActions(game).get(3).getClass());
		assertEquals(MoveToNext.class, state.getAcceptableActions(game).get(4).getClass());
	}
}

