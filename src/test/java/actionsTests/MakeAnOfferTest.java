package actionsTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.actions.marketActions.MakeAnOffer;
import server.model.gameTable.Assistant;
import server.model.market.Offer;
import server.model.player.Player;
import server.model.stateMachine.SellingState;

public class MakeAnOfferTest {
	
	
	@Test(expected=NullPointerException.class)
	public void testIfThrowsExceptionWithNullPrice() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.getCurrentPlayer().incrementAssistants(2);
		game.setState(new SellingState());
		MakeAnOffer action= new MakeAnOffer();
		action.executeAction(game);
	}

	@Test
	public void testExecuteAction() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		Player b = new Player("Andre");
		a.setPlayerNumber(1);
		b.setPlayerNumber(2);
		players.add(a);
		players.add(b);
		game.start(players);
		game.getMarket().shuffleBuyingPlayerList();
		game.getMarket().getSellingPlayerList().add(a);
		game.getMarket().getSellingPlayerList().add(b);
		game.getMarket().sortSellingPlayerList();
		game.getCurrentPlayer().incrementAssistants(2);
		game.setState(new SellingState());
		MakeAnOffer action= new MakeAnOffer();
		Assistant assistant= new Assistant();
		Offer offer= new Offer(a, assistant, 1);
		action.addOfferToList(offer);
		action.executeAction(game);
		assertTrue(action.executeAction(game));
		assertEquals(game.getMarket().getOffersList().get(0), new Offer(a, assistant, 2));
		assertEquals(game.getState().getClass(), SellingState.class);
	}

}
