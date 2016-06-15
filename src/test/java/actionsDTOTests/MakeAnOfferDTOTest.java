package actionsDTOTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.actionsParametersSetters.MakeAnOfferParser;
import client.modelDTO.actionsDTO.marketActions.MakeAnOfferDTO;
import client.modelDTO.marketDTO.OfferDTO;

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
