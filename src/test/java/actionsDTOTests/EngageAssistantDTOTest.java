package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import modelDTO.actionsDTO.standardActions.EngageAssistantDTO;

public class EngageAssistantDTOTest {

	@Test
	public void testToString() {
		EngageAssistantDTO action= new EngageAssistantDTO();
		assertEquals("q1: engage an assistant", action.toString());
	}

}
