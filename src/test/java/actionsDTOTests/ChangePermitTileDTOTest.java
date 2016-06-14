package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.actionsParametersSetters.ChangePermitTilesParser;
import client.modelDTO.actionsDTO.standardActions.ChangePermitTilesDTO;
import client.modelDTO.gameTableDTO.RegionDTO;

public class ChangePermitTileDTOTest {

	@Test
	public void test() {
		RegionDTO selectedRegion= new RegionDTO();
		GameDTO game= new GameDTO();
		ChangePermitTilesDTO action= new ChangePermitTilesDTO();
		action.setSelectedRegion(selectedRegion);
		assertFalse(action.checkIfParametersSetted());
		assertEquals(ChangePermitTilesParser.class, action.setParser(null, null).getClass());
		assertTrue(action.getSelectedRegion()==selectedRegion);
		action.parametersSetted();
		assertTrue(action.checkIfParametersSetted());
		assertEquals("q2: change the permit tiles of a region", action.toString());
	}

}
