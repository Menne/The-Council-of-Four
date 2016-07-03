package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.actionsDTO.standardActions.AdditionalMainActionDTO;
import server.model.actions.standardActions.AdditionalMainAction;


public class AddictionalMainActionDTOTest {

	@Test
	public void testToString() {
		AdditionalMainActionDTO action= new AdditionalMainActionDTO();
		assertEquals("q4: get an additionalo main action", action.toString());
		assertEquals(AdditionalMainAction.class, action.startMapper(null).getClass());
	}

}
