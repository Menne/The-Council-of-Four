package modelDTOTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import client.modelDTO.gameTableDTO.BonusTileDTO;
import client.modelDTO.gameTableDTO.CouncillorDTO;
import client.modelDTO.gameTableDTO.GameTableDTO;
import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import server.model.bonus.Bonus;
import server.model.bonus.ScoreBonus;

public class GameTableDTOTest {

	@Test
	public void testGettersAndSetters() {
		GameTableDTO gameTable= new GameTableDTO();
		ArrayList<RegionDTO> clientRegions= new ArrayList<>();
		CouncillorDTO[] clientKingBalcony= new CouncillorDTO[4];
		List<CouncillorDTO> clientCouncillorReserve= new ArrayList<>();
		ArrayList<Set<Bonus>> clientNobilityTrack= new ArrayList<>();
		ArrayList<GenericPlayerDTO> players= new ArrayList<>();
		String currentPlayer="a";
		String king="Arkon";
		BonusTileDTO nextKingRewardTile= new BonusTileDTO(king, new ScoreBonus(5));
		Set<BonusTileDTO> colourBonuses= new HashSet<>();
		gameTable.setNextKingRewardTile(nextKingRewardTile);
		gameTable.setColourBonuses(colourBonuses);
		gameTable.setClientCouncillorReserve(clientCouncillorReserve);
		gameTable.setClientKingBalcony(clientKingBalcony);
		gameTable.setClientNobilityTrack(clientNobilityTrack);
		gameTable.setClientPlayers(players);
		gameTable.setClientRegions(clientRegions);
		gameTable.setCurrentPlayer(currentPlayer);
		gameTable.setKing(king);
		assertTrue(gameTable.getNextKingRewardTile()==nextKingRewardTile);
		assertTrue(gameTable.getColourBonuses()==colourBonuses);
		assertTrue(gameTable.getClientCouncillorReserve()==clientCouncillorReserve);
		assertTrue(gameTable.getClientKingBalcony()==clientKingBalcony);
		assertTrue(gameTable.getClientNobilityTrack()==clientNobilityTrack);
		assertTrue(gameTable.getClientPlayers()==players);
		assertTrue(gameTable.getClientRegions()==clientRegions);
		assertTrue(gameTable.getCurrentPlayer()==currentPlayer);
		assertTrue(gameTable.getKing()==king);
		assertEquals("\nUpdated game table:\nPlayers:\n" + players + "\nNow is plaiyng:\t" + currentPlayer + "\n" +
				 clientRegions + "\nKingCity: "+ king+" \nKing's balcony:" + Arrays.toString(clientKingBalcony) + "\nCouncillors riserve"+
				 clientCouncillorReserve + "\nNobilityTrack: " + clientNobilityTrack
				 +"\nNextKingRewardTile: "+nextKingRewardTile+"\nColourBonuses: "+colourBonuses, gameTable.toString());
	}

}
