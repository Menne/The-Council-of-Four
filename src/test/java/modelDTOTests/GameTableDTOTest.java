package modelDTOTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.GameTableDTO;
import modelDTO.gameTableDTO.GenericPlayerDTO;
import modelDTO.gameTableDTO.RegionDTO;
import server.model.bonus.Bonus;

public class GameTableDTOTest {

	@Test
	public void testGettersAndSetters() {
		List<RegionDTO> regions= new ArrayList<>();
		CardColourDTO[] clientKingBalcony= new CardColourDTO[4];
		List<CardColourDTO> cardColours= new ArrayList<>();
		List<Set<Bonus>> bonuses= new ArrayList<>();
		List<GenericPlayerDTO> players= new ArrayList<>();
		GameTableDTO gameTable= new GameTableDTO();
		String player= "andre";
		String king= "king";
		gameTable.setClientCouncillorReserve(cardColours);
		gameTable.setClientKingBalcony(clientKingBalcony);
		gameTable.setClientNobilityTrack(bonuses);
		gameTable.setClientPlayers(players);
		gameTable.setClientRegions(regions);
		gameTable.setCurrentPlayer(player);
		gameTable.setKing(king);
		assertTrue(gameTable.getClientCouncillorReserve()==cardColours);
		assertTrue(gameTable.getClientKingBalcony()==clientKingBalcony);
		assertTrue(gameTable.getClientNobilityTrack()==bonuses);
		assertTrue(gameTable.getClientPlayers()==players);
		assertTrue(gameTable.getClientRegions()==regions);
		assertTrue(gameTable.getCurrentPlayer()==player);
		assertTrue(gameTable.getKing()==king);
		
	}

}
