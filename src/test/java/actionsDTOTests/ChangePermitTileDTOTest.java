package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.actionsDTO.standardActions.ChangePermitTilesDTO;
import client.modelDTO.actionsParametersSetters.ChangePermitTilesParametersSetter;
import client.modelDTO.gameTableDTO.RegionDTO;

public class ChangePermitTileDTOTest {

	@Test
	public void test() {
		RegionDTO selectedRegion= new RegionDTO();
		ChangePermitTilesDTO action= new ChangePermitTilesDTO();
		action.setSelectedRegion(selectedRegion);
		assertFalse(action.checkIfParametersSet());
		assertEquals(ChangePermitTilesParametersSetter.class, action.setParser().getClass());
		assertTrue(action.getSelectedRegion()==selectedRegion);
		action.parametersSet();
		assertTrue(action.checkIfParametersSet());
		assertEquals("q2: change the permit tiles of a region", action.toString());
	}

}
