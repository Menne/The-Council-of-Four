package DTOTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.gameTableDTO.CardColourDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.playerDTO.AssistantDTO;
import client.modelDTO.playerDTO.ClientPlayerDTO;

public class ClientPlayerDTOTest {

	@Test
	public void test() {
		List<CardColourDTO> colour= new ArrayList<>();
		List<PermitTileDTO> coveredTiles= new ArrayList<>();
		List<PermitTileDTO> uncoveredTiles= new ArrayList<>();
		List<AssistantDTO> assistants= new ArrayList<>();
		ClientPlayerDTO player= new ClientPlayerDTO();
		int playerNumber=1;
		String name="andrea";
		player.setAssistants(assistants);
		player.setAvailablePermitTiles(uncoveredTiles);
		player.setCoveredPermitTiles(coveredTiles);
		player.setHand(colour);
		player.setPlayerNumber(playerNumber);
		player.setName(name);
		assertTrue(player.getAssistants()==assistants);
		assertTrue(player.getAvailablePermitTiles()==uncoveredTiles);
		assertTrue(player.getCoveredPermitTiles()==coveredTiles);
		assertTrue(player.getHand()==colour);
		assertTrue(player.getName()==name);
		assertTrue(player.getPlayerNumber()==playerNumber);
	} 

}
