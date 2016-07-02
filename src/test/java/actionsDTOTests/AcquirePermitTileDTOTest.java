package actionsDTOTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.actionsDTO.actionsParametersSetters.AcquirePermitTileParser;
import client.modelDTO.actionsDTO.standardActions.AcquirePermitTileDTO;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;
import client.modelDTO.gameTableDTO.RegionDTO;

public class AcquirePermitTileDTOTest {

	@Test
	public void testSetters() {
		Integer numberOfPermitTile=0;
		RegionDTO chosenRegion= new RegionDTO();
		List<PoliticsCardDTO> cardsToDescard= new ArrayList<>();
		AcquirePermitTileDTO action= new AcquirePermitTileDTO();
		action.setCardsToDescard(cardsToDescard);
		action.setChosenRegion(chosenRegion);
		action.setNumberOfPermitTile(numberOfPermitTile);
		assertFalse(action.checkIfParametersSet());
		assertEquals(AcquirePermitTileParser.class, action.setParser().getClass());
		action.parametersSet();
		assertTrue(action.checkIfParametersSet());
		assertTrue(action.getCardsToDescard()==cardsToDescard);
		assertTrue(action.getChoosenRegion()==chosenRegion);
		assertTrue(action.getNumberOfPermitTiles()==numberOfPermitTile);
		assertEquals("m2: acquire a permit tile", action.toString());
	}

}
