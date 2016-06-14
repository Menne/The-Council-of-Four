package modelDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.marketDTO.MarketableDTO;
import client.modelDTO.marketDTO.OfferDTO;

public class OfferDTOTest {

	@Test
	public void testGettersSettersAndToString() {
		OfferDTO offer= new OfferDTO();
		String offeringPlayer="andre";
		MarketableDTO offeredObjectDTO= new MarketableDTO() {
			private static final long serialVersionUID = 1L;
		};
		int price=0;
		offer.setOfferedObjectDTO(offeredObjectDTO);
		offer.setOfferingPlayer(offeringPlayer);
		offer.setPrice(price);
		assertTrue(offer.getOfferedObjectDTO()==offeredObjectDTO);
		assertTrue(offer.getOfferingPlayer()==offeringPlayer);
		assertTrue(offer.getPrice()==price);
		assertEquals("Who is offering: " + offeringPlayer + ", Object: " + offeredObjectDTO
				+ ", price: " + price, offer.toString());
	}

}
