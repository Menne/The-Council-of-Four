package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.actionsParametersSetters.ElectCouncillorParser;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorDTO;
import client.modelDTO.gameTableDTO.CardColourDTO;

public class ElectCouncillorDTOTest {

	@Test
	public void testSettersAndToString() {
		CardColourDTO newCouncillor= new CardColourDTO();
		CardColourDTO[] councilBalcony= new CardColourDTO[4];
		GameDTO game= new GameDTO();
		ElectCouncillorDTO action= new ElectCouncillorDTO();
		action.setCouncilBalcony(councilBalcony);
		action.setNewCouncillor(newCouncillor);
		assertFalse(action.checkIfParametersSetted());
		assertEquals(ElectCouncillorParser.class, action.setParser(null, null).getClass());
		action.parametersSetted();
		assertTrue(action.checkIfParametersSetted());
		assertTrue(action.getCouncilBalcony()==councilBalcony);
		assertTrue(action.getNewCouncillor()==newCouncillor);
		assertEquals("m1: elect a councillor", action.toString());
	}

}
