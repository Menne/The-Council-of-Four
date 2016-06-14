package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import modelDTO.actionsDTO.marketActions.AcceptAnOfferDTO;
import modelDTO.marketDTO.OfferDTO;

public class AcceptAnOfferDTOTest {

	@Test
	public void testSetters() {
		OfferDTO offerDTO= new OfferDTO();
		AcceptAnOfferDTO action= new AcceptAnOfferDTO();
		assertFalse(action.checkIfParametersSetted());
		action.setOffer(offerDTO);
		action.parametersSetted();
		assertTrue(action.getOffer()==offerDTO);
		assertTrue(action.checkIfParametersSetted());
		assertEquals("ao: accept an offer", action.toString());
	}

}
