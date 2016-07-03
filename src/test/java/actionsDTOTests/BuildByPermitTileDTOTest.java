package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.actionsDTO.standardActions.BuildByPermitTileDTO;
import client.modelDTO.actionsParametersSetters.BuildByPermitTileParametersSetter;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;

public class BuildByPermitTileDTOTest {

	@Test
	public void test() {
		BuildByPermitTileDTO action= new BuildByPermitTileDTO();
		PermitTileDTO selectedPermitTile= new PermitTileDTO();
		CityDTO selectedCity= new CityDTO();
		action.setSelectedCity(selectedCity);
		action.setSelectedPermitTile(selectedPermitTile);
		assertTrue(action.getSelectedCity()==selectedCity);
		assertTrue(action.getSelectedPermitTile()==selectedPermitTile);
		assertFalse(action.checkIfParametersSet());
		action.parametersSet();
		assertTrue(action.checkIfParametersSet());
		assertEquals("m3: build an emporium using a permit tile", action.toString());
		assertEquals(BuildByPermitTileParametersSetter.class, action.setParser().getClass());
	}

}
