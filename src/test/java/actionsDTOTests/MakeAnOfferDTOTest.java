package actionsDTOTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.actionsDTO.marketActions.MakeAnOfferDTO;
import client.modelDTO.actionsParametersSetters.MakeAnOfferParametersSetter;
import client.modelDTO.marketDTO.OfferDTO;

public class MakeAnOfferDTOTest {

	@Test
	public void testSettersAndToString() {
		List<OfferDTO> offeredObjectsDTO= new ArrayList<>();
		OfferDTO offerDTO= new OfferDTO();
		MakeAnOfferDTO action= new MakeAnOfferDTO();
		action.setOfferedObjectsDTO(offeredObjectsDTO);
		assertFalse(action.checkIfParametersSet());
		assertEquals(MakeAnOfferParametersSetter.class, action.setParser().getClass());
		assertTrue(action.getOfferedObjectsDTO()==offeredObjectsDTO);
		action.addOfferToList(offerDTO);
		assertTrue(action.getOfferedObjectsDTO().get(0)==offerDTO);
		action.parametersSet();
		assertTrue(action.checkIfParametersSet());
		assertEquals("mo: make an offer", action.toString());
	}

}
