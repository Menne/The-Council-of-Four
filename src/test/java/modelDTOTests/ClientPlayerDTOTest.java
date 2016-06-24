package modelDTOTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.gameTableDTO.BonusTileDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;
import client.modelDTO.playerDTO.AssistantDTO;
import client.modelDTO.playerDTO.ClientPlayerDTO;

public class ClientPlayerDTOTest {

	@Test
	public void test() {
		List<PoliticsCardDTO> colour= new ArrayList<>();
		List<PermitTileDTO> coveredTiles= new ArrayList<>();
		List<PermitTileDTO> uncoveredTiles= new ArrayList<>();
		List<AssistantDTO> assistants= new ArrayList<>();
		List<BonusTileDTO> finalBonuses= new ArrayList<>();
		ClientPlayerDTO player= new ClientPlayerDTO();
		int playerNumber=1;
		int nobility= 10;
		int score= 10;
		int coins=10;
		String name="andrea";
		player.setFinalBonuses(finalBonuses);
		player.setNobility(nobility);
		player.setCoins(coins);
		player.setScore(score);
		player.setAssistants(assistants);
		player.setAvailablePermitTiles(uncoveredTiles);
		player.setCoveredPermitTiles(coveredTiles);
		player.setHand(colour);
		player.setPlayerNumber(playerNumber);
		player.setName(name);
		assertTrue(player.getNobility()==nobility);
		assertTrue(player.getScore()==score);
		assertTrue(player.getCoins()==coins);
		assertTrue(player.getFinalBonuses()==finalBonuses);
		assertTrue(player.getAssistants()==assistants);
		assertTrue(player.getAvailablePermitTiles()==uncoveredTiles);
		assertTrue(player.getCoveredPermitTiles()==coveredTiles);
		assertTrue(player.getHand()==colour);
		assertTrue(player.getName()==name);
		assertTrue(player.getPlayerNumber()==playerNumber);
		assertEquals("\n" + name +", Here is your current status: [score=" + score + ", hand=" + colour + ", coveredPermitTiles=" + coveredTiles
				+ ", availablePermitTiles=" + uncoveredTiles + ", assistants=" + assistants + ", coins=" + coins + ", nobility=" + nobility + 
				", finalBonuses: "+finalBonuses +"]", player.toString());
	}
}
