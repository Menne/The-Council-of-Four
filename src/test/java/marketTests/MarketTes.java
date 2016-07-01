package marketTests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.model.market.Market;
import server.model.market.Offer;
import server.model.player.Assistant;
import server.model.player.Player;

public class MarketTes {

	@Test
	public void test() {
		Market market= new Market();
		Assistant assistant= new Assistant();
		Player a= new Player("A");
		Player b= new Player("B");
		Offer offer= new Offer(a, assistant, 2);
		assertTrue(market.getOffersList().isEmpty());
		market.addOffer(offer);
		assertTrue(market.getOffersList().get(0)==offer);
		market.removeOffer(offer);
		assertTrue(market.getOffersList().isEmpty());
		market.addPlayer(a);
		assertTrue(market.getBuyingPlayerList().get(0)==a);
		market.addPlayer(b);
		int oldSize= market.getBuyingPlayerList().size();
		assertTrue(oldSize==market.getBuyingPlayerList().size());
		assertTrue(market.getSellingPlayerList().isEmpty());
		market.getSellingPlayerList().add(b);
		market.clearMarket();
		assertTrue(market.getBuyingPlayerList().isEmpty());
		assertTrue(market.getOffersList().isEmpty());
		assertTrue(market.getOffersList().isEmpty());
	}

}