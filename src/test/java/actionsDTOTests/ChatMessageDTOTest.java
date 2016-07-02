package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.actionsDTO.ChatMessageDTO;

public class ChatMessageDTOTest {

	@Test
	public void test() {
		String message="etc";
		ChatMessageDTO action= new ChatMessageDTO(message);
		assertTrue(action.getMessage()==message);
	}

}
