package modelDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.gameTableDTO.EmporiumDTO;

public class EmporiumDTOTest {

	@Test
	public void test() {
		int playerNumber=1;
		String playerName="a";
		EmporiumDTO emporium= new EmporiumDTO();
		EmporiumDTO emporium1= new EmporiumDTO(playerNumber);
		emporium.setPlayerName(playerName);
		emporium.setPlayerNumber(playerNumber);
		assertTrue(emporium.getPlayerNumber()==playerNumber);
		assertTrue(emporium.getPlayerName()==playerName);
		assertEquals(emporium, emporium1);
		assertEquals("EmporiumDTO [playerName=" + playerName + "]", emporium.toString());
	}

}
