package modelDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.playerDTO.AssistantDTO;

public class AssistantDTOTest {

	@Test
	public void test() {
		AssistantDTO assistant= new AssistantDTO();
		assertEquals("AssistantDTO", assistant.toString());
	}

}
