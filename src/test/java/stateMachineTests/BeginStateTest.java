
package stateMachineTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.actions.PickPoliticsCard;
import server.model.player.Player;
import server.model.stateMachine.BeginState;
import server.model.stateMachine.SellingState;
import server.model.stateMachine.State11;

public class BeginStateTest {

	@Test
	public void testPickPoliticsCardsTransition() {
		BeginState state= new BeginState();
		assertEquals(state.pickPoliticsCardTransition().getClass(), State11.class);
	}
	
	@Test
	public void testGetAcceptableActions() {
		BeginState state= new BeginState();
		Game game= new Game();
		assertTrue(PickPoliticsCard.class==state.getAcceptableActions(game).get(0).getClass());
	}
	
	@Test
	public void moveToNextTransition() throws IOException {
		BeginState state= new BeginState();
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		Player b = new Player("Andre");
		b.setPlayerNumber(2);
		players.add(a);
		players.add(b);
		game.start(players);
		game.setCurrentPlayer(a);
		assertTrue(BeginState.class== state.moveToNextTransition(game).getClass());
		assertTrue(game.getCurrentPlayer()==b);
		assertTrue(SellingState.class==state.moveToNextTransition(game).getClass());
		assertTrue(game.getCurrentPlayer()==a);
		assertTrue(game.getMarket().getSellingPlayerList().size()==1);
	}

}
