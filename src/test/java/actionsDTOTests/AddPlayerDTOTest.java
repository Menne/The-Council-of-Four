package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import modelDTO.actionsDTO.AddPlayerDTO;

public class AddPlayerDTOTest {

	@Test
	public void testGetter() {
		String playerName= "andre";
		AddPlayerDTO action= new AddPlayerDTO(playerName);
		assertTrue(action.getPlayerName()==playerName);
	}
}
