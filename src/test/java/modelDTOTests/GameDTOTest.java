package modelDTOTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.gameTableDTO.GameTableDTO;
import client.modelDTO.marketDTO.MarketDTO;
import client.modelDTO.playerDTO.ClientPlayerDTO;

public class GameDTOTest {

	@Test
	public void testGettersAnSetters() {
		GameDTO game= new GameDTO();
		GameTableDTO gameTable=new GameTableDTO();
		game.setClientGameTable(gameTable);
		List<ActionDTO> actions= new ArrayList<>();
		game.setAvailableActions(actions);
		ClientPlayerDTO player= new ClientPlayerDTO();
		game.setClientPlayer(player);
		MarketDTO market= new MarketDTO();
		game.setMarket(market);
		assertTrue(game.getClientPlayer()==player);
		assertTrue(game.getAvailableActions()==actions);
		assertTrue(game.getMarket()==market);
		assertTrue(game.getClientGameTable()==gameTable);
	}

}
