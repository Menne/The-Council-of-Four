package actionsDTOTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.actionsParametersSetters.AcquirePermitTileParser;
import client.modelDTO.actionsDTO.standardActions.AcquirePermitTileDTO;
import client.modelDTO.gameTableDTO.CardColourDTO;
import client.modelDTO.gameTableDTO.RegionDTO;

public class AcquirePermitTileDTOTest {

	@Test
	public void testSetters() {
		Integer numberOfPermitTile=0;
		RegionDTO chosenRegion= new RegionDTO();
		List<CardColourDTO> cardsToDescard= new ArrayList<>();
		GameDTO game= new GameDTO();
		AcquirePermitTileDTO action= new AcquirePermitTileDTO();
		action.setCardsToDescard(cardsToDescard);
		action.setChosenRegion(chosenRegion);
		action.setNumberOfPermitTile(numberOfPermitTile);
		assertFalse(action.checkIfParametersSetted());
		assertEquals(AcquirePermitTileParser.class, action.setParser(null, null).getClass());
		action.parametersSetted();
		assertTrue(action.checkIfParametersSetted());
		assertTrue(action.getCardsToDescard()==cardsToDescard);
		assertTrue(action.getChoosenRegion()==chosenRegion);
		assertTrue(action.getNumberOfPermitTiles()==numberOfPermitTile);
		assertEquals("m2: acquire a permit tile", action.toString());
	}

}
