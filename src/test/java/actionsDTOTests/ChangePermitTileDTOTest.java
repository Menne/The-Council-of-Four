package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import modelDTO.GameDTO;
import modelDTO.actionsDTO.standardActions.ChangePermitTilesDTO;
import modelDTO.gameTableDTO.RegionDTO;
import modelDTO.parser.ChangePermitTilesParser;

public class ChangePermitTileDTOTest {

	@Test
	public void test() {
		RegionDTO selectedRegion= new RegionDTO();
		GameDTO game= new GameDTO();
		ChangePermitTilesDTO action= new ChangePermitTilesDTO();
		action.setSelectedRegion(selectedRegion);
		assertFalse(action.checkIfParametersSetted());
		assertEquals(ChangePermitTilesParser.class, action.setParser(game).getClass());
		assertTrue(action.getSelectedRegion()==selectedRegion);
		action.parametersSetted();
		assertTrue(action.checkIfParametersSetted());
		assertEquals("q2: change the permit tiles of a region", action.toString());
	}

}
