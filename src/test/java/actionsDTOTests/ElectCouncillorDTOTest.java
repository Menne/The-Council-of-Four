package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.actionsDTO.actionsParametersSetters.ElectCouncillorParser;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorDTO;
import client.modelDTO.gameTableDTO.CouncillorDTO;

public class ElectCouncillorDTOTest {

	@Test
	public void testSettersAndToString() {
		CouncillorDTO newCouncillor= new CouncillorDTO();
		CouncillorDTO[] councilBalcony= new CouncillorDTO[4];
		ElectCouncillorDTO action= new ElectCouncillorDTO();
		action.setCouncilBalcony(councilBalcony);
		action.setNewCouncillor(newCouncillor);
		assertFalse(action.checkIfParametersSet());
		assertEquals(ElectCouncillorParser.class, action.setParser().getClass());
		action.parametersSet();
		assertTrue(action.checkIfParametersSet());
		assertTrue(action.getCouncilBalcony()==councilBalcony);
		assertTrue(action.getNewCouncillor()==newCouncillor);
		assertEquals("m1: elect a councillor", action.toString());
	}

}
