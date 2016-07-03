package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.actionsDTO.standardActions.ElectCouncillorByAssistantDTO;
import client.modelDTO.actionsParametersSetters.ElectCouncillorByAssistantParametersSetter;
import client.modelDTO.gameTableDTO.CouncillorDTO;

public class ElectCouncillorByAssistantDTOTest {

	@Test
	public void testSettersAndToString() {
		CouncillorDTO newCouncillor= new CouncillorDTO();
		CouncillorDTO[] councilBalcony= new CouncillorDTO[4];
		ElectCouncillorByAssistantDTO action= new ElectCouncillorByAssistantDTO();
		action.setCouncilBalcony(councilBalcony);
		action.setNewCouncillor(newCouncillor);
		assertFalse(action.checkIfParametersSet());
		assertEquals(ElectCouncillorByAssistantParametersSetter.class, action.setParser().getClass());
		action.parametersSet();
		assertTrue(action.checkIfParametersSet());
		assertTrue(action.getCouncilBalcony()==councilBalcony);
		assertTrue(action.getNewCouncillor()==newCouncillor);
		assertEquals("q3: elect a councillor by sending an assistant", action.toString());
	}

}
