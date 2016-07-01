package actionsTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import server.model.Game;
import server.model.actions.marketActions.AcceptAnOffer;
import server.model.market.Offer;
import server.model.player.Assistant;
import server.model.player.Player;
import server.model.stateMachine.BuyingState;

public class AcceptAnOfferTest {

	@Test(expected=NullPointerException.class)
	public void testIfExecuteActionThrowsNullPointerException() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.setState(new BuyingState());
		AcceptAnOffer action = new AcceptAnOffer();
		action.executeAction(game);
	}
	
	@Test
	public void testOfferGetterAndSetter() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		Player b = new Player("Luca");
		a.setPlayerNumber(1);
		b.setPlayerNumber(2);
		players.add(a);
		players.add(b);
		game.start(players);
		game.setState(new BuyingState());
		game.setCurrentPlayer(a);
		AcceptAnOffer action = new AcceptAnOffer();
		Assistant assistant= new Assistant();
		Offer offer= new Offer(b, assistant, 1);
		action.setOffer(offer);
		assertTrue(action.getOffer()==offer);
		int oldNumberOfAssistantofA= a.getNumberOfAssistants();
		int oldNumberOfAssistantofB= b.getNumberOfAssistants();
		int oldCoinsA= a.getCoins();
		int oldCoinsB= b.getCoins();
		assertTrue(action.executeAction(game));
		assertTrue(a.getNumberOfAssistants()==oldNumberOfAssistantofA+1);
		assertTrue(b.getNumberOfAssistants()==oldNumberOfAssistantofB-1);
		assertTrue(a.getCoins()==oldCoinsA-1);
		assertTrue(b.getCoins()==oldCoinsB+1);
	}
	

}
