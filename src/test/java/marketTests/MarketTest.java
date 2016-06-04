
package marketTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.gameTable.Assistant;
import server.model.market.Market;
import server.model.market.Offer;
import server.model.stateMachine.SellingState;

public class MarketTest {

	@Test
	public void test() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player();
		Player b= new Player();
		players.add(a);
		players.add(b);
		game.start(players);
		game.setCurrentPlayer(a);
		game.setState(new SellingState());
		List<Player> sellingPlayers= new ArrayList<>();
		List<Player> buyingPlayers= new ArrayList<>();
		sellingPlayers.add(a);
		buyingPlayers.add(b);
		Market market= new Market(players);
	//	market.sellingNextPlayer();
		assertTrue(players==market.getSellingPlayerList());
		assertTrue(market.getBuyingPlayerList().contains(players.get(0)));
	//	assertTrue(a==market.getCurrentPlayer());
		Assistant assistant= new Assistant();
		assistant.addObjectToPlayer(a);
		Offer offer= new Offer(a, assistant, 5);
		market.addOffer(a, assistant, 5);
		assertEquals(offer, market.getOffersList().get(0));
		int oldCoinsBuyer= b.getCoins();
		int oldCoinsSeller=a.getCoins();
		int OldNumberOfAssistantsBuyer= b.getNumberOfAssistants();
		int OldNumberOfAssistantsSeller=a.getNumberOfAssistants();
		market.buyOffer(market.getOffersList().get(0), b);
		assertEquals(oldCoinsBuyer-5, b.getCoins());
		assertEquals(oldCoinsSeller+5, a.getCoins());
		assertEquals(OldNumberOfAssistantsBuyer+1, b.getNumberOfAssistants());
		assertEquals(OldNumberOfAssistantsSeller-1, a.getNumberOfAssistants());
		assertTrue(market.getOffersList().isEmpty());
		Player c= new Player();
		market.addPlayer(c);
		assertTrue(market.getBuyingPlayerList().contains(c));
	/*	assertFalse(market.isSellingPhaseFinished());
		market.sellingNextPlayer();
		assertTrue(market.isSellingPhaseFinished());
		market.buyingNextPlayer();
		assertTrue(market.getCurrentPlayer()==a);
		market.buyingNextPlayer();
		assertFalse(market.isBuyingPhaseFinished());
		market.buyingNextPlayer();
		assertTrue(market.isBuyingPhaseFinished()); */
		market.addOffer(a, assistant, 2);
		market.clearMarket();
		assertTrue(market.getOffersList().isEmpty());
	}

}
