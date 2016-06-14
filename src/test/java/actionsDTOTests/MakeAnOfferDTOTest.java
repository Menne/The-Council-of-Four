package actionsDTOTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import modelDTO.GameDTO;
import modelDTO.actionsDTO.marketActions.MakeAnOfferDTO;
import modelDTO.marketDTO.OfferDTO;
import modelDTO.parser.MakeAnOfferParser;

public class MakeAnOfferDTOTest {

	@Test
	public void testSettersAndToString() {
		List<OfferDTO> offeredObjectsDTO= new ArrayList<>();
		OfferDTO offerDTO= new OfferDTO();
		GameDTO game=new GameDTO();
		MakeAnOfferDTO action= new MakeAnOfferDTO();
		action.setOfferedObjectsDTO(offeredObjectsDTO);
		assertFalse(action.checkIfParametersSetted());
		assertEquals(MakeAnOfferParser.class, action.setParser(null, null).getClass());
		assertTrue(action.getOfferedObjectsDTO()==offeredObjectsDTO);
		action.addOfferToList(offerDTO);
		assertTrue(action.getOfferedObjectsDTO().get(0)==offerDTO);
		action.parametersSetted();
		assertTrue(action.checkIfParametersSetted());
		assertEquals("mo: make an offer", action.toString());
	}

}
