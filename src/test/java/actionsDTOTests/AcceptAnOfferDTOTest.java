package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import modelDTO.GameDTO;
import modelDTO.actionsDTO.marketActions.AcceptAnOfferDTO;
import modelDTO.marketDTO.OfferDTO;
import modelDTO.parser.AcceptAnOfferParser;

public class AcceptAnOfferDTOTest {

	@Test
	public void testSetters() {
		OfferDTO offerDTO= new OfferDTO();
		GameDTO game= new GameDTO();
		AcceptAnOfferDTO action= new AcceptAnOfferDTO();
		assertEquals(AcceptAnOfferParser.class, action.setParser(game).getClass());
		action.setOffer(offerDTO);
		action.parametersSetted();
		assertTrue(action.getOffer()==offerDTO);
		assertTrue(action.checkIfParametersSetted());
	}

}
