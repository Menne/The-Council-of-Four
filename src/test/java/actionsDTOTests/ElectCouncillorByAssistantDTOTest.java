package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.actionsDTO.actionsParametersSetters.ElectCouncillorByAssistantParser;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorByAssistantDTO;
import client.modelDTO.gameTableDTO.CardColourDTO;

public class ElectCouncillorByAssistantDTOTest {

	@Test
	public void testSettersAndToString() {
		CardColourDTO newCouncillor= new CardColourDTO();
		CardColourDTO[] councilBalcony= new CardColourDTO[4];
		ElectCouncillorByAssistantDTO action= new ElectCouncillorByAssistantDTO();
		action.setCouncilBalcony(councilBalcony);
		action.setNewCouncillor(newCouncillor);
		assertFalse(action.checkIfParametersSetted());
		assertEquals(ElectCouncillorByAssistantParser.class, action.setParser().getClass());
		action.parametersSetted();
		assertTrue(action.checkIfParametersSetted());
		assertTrue(action.getCouncilBalcony()==councilBalcony);
		assertTrue(action.getNewCouncillor()==newCouncillor);
		assertEquals("q3: elect a councillor by sending an assistant", action.toString());
	}

}
