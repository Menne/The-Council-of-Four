package actionsDTOTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import modelDTO.GameDTO;
import modelDTO.actionsDTO.standardActions.AcquirePermitTileDTO;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.RegionDTO;
import modelDTO.parser.AcquirePermitTileParser;

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
		assertEquals(AcquirePermitTileParser.class, action.setParser(game).getClass());
		action.parametersSetted();
		assertTrue(action.checkIfParametersSetted());
		assertTrue(action.getCardsToDescard()==cardsToDescard);
		assertTrue(action.getChoosenRegion()==chosenRegion);
		assertTrue(action.getNumberOfPermitTiles()==numberOfPermitTile);
	}

}
