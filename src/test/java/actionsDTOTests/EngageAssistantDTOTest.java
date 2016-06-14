package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.actionsDTO.standardActions.EngageAssistantDTO;
import server.model.actions.standardActions.EngageAssistant;

public class EngageAssistantDTOTest {

	@Test
	public void testToString() {
		EngageAssistantDTO action= new EngageAssistantDTO();
		assertEquals("q1: engage an assistant", action.toString());
		assertEquals(EngageAssistant.class, action.startMapper(null).getClass());
	}

}
