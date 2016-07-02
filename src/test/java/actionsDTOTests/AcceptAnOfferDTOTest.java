package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.actionsDTO.marketActions.AcceptAnOfferDTO;
import client.modelDTO.marketDTO.OfferDTO;

public class AcceptAnOfferDTOTest {

	@Test
	public void testSetters() {
		OfferDTO offerDTO= new OfferDTO();
		AcceptAnOfferDTO action= new AcceptAnOfferDTO();
		assertFalse(action.checkIfParametersSet());
		action.setOffer(offerDTO);
		action.parametersSet();
		assertTrue(action.getOffer()==offerDTO);
		assertTrue(action.checkIfParametersSet());
		assertEquals("ao: accept an offer", action.toString());
	}

}
