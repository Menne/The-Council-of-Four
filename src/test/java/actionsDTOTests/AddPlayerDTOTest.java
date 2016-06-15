package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.actionsDTO.AddPlayerDTO;

public class AddPlayerDTOTest {

	@Test
	public void testGetter() {
		String playerName= "andre";
		AddPlayerDTO action= new AddPlayerDTO(playerName);
		assertTrue(action.getPlayerName()==playerName);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testException() {
		String playerName= "andre";
		AddPlayerDTO action= new AddPlayerDTO(playerName);
		action.startMapper(null);
		assertTrue(false);
	}
}
