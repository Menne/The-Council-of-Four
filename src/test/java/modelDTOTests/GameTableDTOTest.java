package modelDTOTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import client.modelDTO.gameTableDTO.CityColourDTO;
import client.modelDTO.gameTableDTO.CouncillorDTO;
import client.modelDTO.gameTableDTO.GameTableDTO;
import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import server.model.bonus.Bonus;
import server.model.bonus.ScoreBonus;

public class GameTableDTOTest {

	@Test
	public void testGettersAndSetters() {
		List<RegionDTO> regions= new ArrayList<>();
		CouncillorDTO[] clientKingBalcony= new CouncillorDTO[4];
		List<CouncillorDTO> councillors= new ArrayList<>();
		List<Set<Bonus>> bonuses= new ArrayList<>();
		List<GenericPlayerDTO> players= new ArrayList<>();
		GameTableDTO gameTable= new GameTableDTO();
		String player= "andre";
		String king= "king";
		ScoreBonus nextKingRewardTile= new ScoreBonus(5);
		Map<CityColourDTO, ScoreBonus> colourBonuses = null;
		gameTable.setNextKingRewardTile(nextKingRewardTile);
		gameTable.setColourBonuses(colourBonuses);
		gameTable.setClientCouncillorReserve(councillors);
		gameTable.setClientKingBalcony(clientKingBalcony);
		gameTable.setClientNobilityTrack(bonuses);
		gameTable.setClientPlayers(players);
		gameTable.setClientRegions(regions);
		gameTable.setCurrentPlayer(player);
		gameTable.setKing(king);
		assertTrue(gameTable.getNextKingRewardTile()==nextKingRewardTile);
		assertTrue(gameTable.getColourBonuses()==colourBonuses);
		assertTrue(gameTable.getClientCouncillorReserve()==councillors);
		assertTrue(gameTable.getClientKingBalcony()==clientKingBalcony);
		assertTrue(gameTable.getClientNobilityTrack()==bonuses);
		assertTrue(gameTable.getClientPlayers()==players);
		assertTrue(gameTable.getClientRegions()==regions);
		assertTrue(gameTable.getCurrentPlayer()==player);
		assertTrue(gameTable.getKing()==king);
		assertEquals("\nUpdated game table:\nPlayers:\n" + players + "\nNow is plaiyng:\t" + player + "\n" +
				 regions + "\nKingCity: "+ king+" \nKing's balcony:" + Arrays.toString(clientKingBalcony) + "\nCouncillors riserve"+
				 councillors + "\nNobilityTrack: " + bonuses
				 +"\nNextKingRewardTile: "+nextKingRewardTile+"\nColourBonuses: "+colourBonuses, gameTable.toString());
	}

}
