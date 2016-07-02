package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.actionsDTO.actionsParametersSetters.bonusActionsSetters.PickPermitTileBonusParser;
import client.modelDTO.actionsDTO.bonusActions.PickPermitTileActionDTO;
import client.modelDTO.gameTableDTO.RegionDTO;

public class PickPermitTileActionDTOTest {

	@Test
	public void test() {
		RegionDTO selectedRegion= new RegionDTO();
		Integer numberOfPermitTile= 1;
		PickPermitTileActionDTO action= new PickPermitTileActionDTO();
		assertFalse(action.checkIfParametersSet());
		action.setNumberOfPermitTile(numberOfPermitTile);
		action.setSelectedRegion(selectedRegion);
		assertTrue(action.getNumberOfPermitTiles()== numberOfPermitTile);
		assertTrue(action.getSelectedRegion()==selectedRegion);
		action.parametersSet();
		assertTrue(action.checkIfParametersSet());
		assertEquals(PickPermitTileBonusParser.class, action.setParser().getClass());
		assertEquals("b2: get bonus!", action.toString());
	}
}
