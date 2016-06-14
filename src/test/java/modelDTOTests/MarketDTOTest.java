package modelDTOTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import modelDTO.marketDTO.MarketDTO;
import modelDTO.marketDTO.OfferDTO;

public class MarketDTOTest {

	@Test
	public void testGettersSettersAndToString() {
		List<OfferDTO> offersList= new ArrayList<>();
		List<String> sellingPlayerList= new ArrayList<>();
		List<String> buyingPlayerList= new ArrayList<>();
		MarketDTO market= new MarketDTO();
		market.setBuyingPlayerList(buyingPlayerList);
		market.setOffersList(offersList);
		market.setSellingPlayerList(sellingPlayerList);
		assertTrue(market.getBuyingPlayerList()==buyingPlayerList);
		assertTrue(market.getOffersList()==offersList);
		assertTrue(market.getSellingPlayerList()==sellingPlayerList);
		assertEquals("What is in the market: " + offersList + "]", market.toString());
	}

}
