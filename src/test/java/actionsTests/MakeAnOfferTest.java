/*package actionsTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.actions.marketActions.MakeAnOffer;
import server.model.gameTable.Assistant;
import server.model.market.Offer;
import server.model.stateMachine.BuyingState;
import server.model.stateMachine.SellingState;
import server.model.stateMachine.State01;

public class MakeAnOfferTest {

	@Test(expected=NullPointerException.class)
	public void testIfThrowsExceptionWithNullObject() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.getCurrentPlayer().incrementAssistants(2);
		game.setState(new State01());
		MakeAnOffer action= new MakeAnOffer();

		action.executeAction(game);
	}
	
	@Test(expected=NullPointerException.class)
	public void testIfThrowsExceptionWithNullPrice() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.getCurrentPlayer().incrementAssistants(2);
		game.setState(new State01());
		MakeAnOffer action= new MakeAnOffer();
		Assistant assistant= new Assistant();
	//	action.setOfferingObject(assistant);
		action.executeAction(game);
	}

	@Test
	public void testExecuteAction() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.getCurrentPlayer().incrementAssistants(2);
		game.setState(new SellingState());
		MakeAnOffer action= new MakeAnOffer();
		Assistant assistant= new Assistant();
	//	action.setOfferingObject(assistant);
	//	action.setPrice(2);
		action.executeAction(game);
		assertTrue(action.executeAction(game));
		assertEquals(game.getMarket().getOffersList().get(0), new Offer(a, assistant, 2));
		assertEquals(game.getState().getClass(), BuyingState.class);
		
	}

	
}*/
