package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import modelDTO.actionsDTO.standardActions.AddictionalMainActionDTO;


public class AddictionalMainActionDTOTest {

	@Test
	public void testToString() {
		AddictionalMainActionDTO action= new AddictionalMainActionDTO();
		assertEquals("q4: get an additionalo main action", action.toString());
	}

}
