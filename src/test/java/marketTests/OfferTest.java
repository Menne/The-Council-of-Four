package marketTests;

import static org.junit.Assert.*;

import org.junit.Test;

import players.Player;
import server.model.gameTable.Assistant;
import server.model.market.Offer;

public class OfferTest {

	@Test
	public void test() {
		Player a=new Player("Andre");
		Assistant assistant=new Assistant();
		Offer offer=new Offer(a, assistant, 5);
		assertTrue(a== offer.getOfferingPlayer());
		assertTrue(assistant== offer.getOfferedObject());
		assertEquals(5, offer.getPrice());
	}

}
