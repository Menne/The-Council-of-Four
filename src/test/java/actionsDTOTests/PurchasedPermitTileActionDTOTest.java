package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;
import client.modelDTO.actionsParametersSetters.bonusActionsParametersSetters.PurchasedPermitTileBonusParametersSetter;
import client.modelDTO.gameTableDTO.PermitTileDTO;

public class PurchasedPermitTileActionDTOTest {

	@Test
	public void testSetters() {
		PermitTileDTO selectedPermitTile= new PermitTileDTO();
		PurchasedPermitTileActionDTO action= new PurchasedPermitTileActionDTO();
		action.setPermitTile(selectedPermitTile);
		assertFalse(action.checkIfParametersSet());
		assertEquals(PurchasedPermitTileBonusParametersSetter.class, action.setParser().getClass());
		action.parametersSet();
		assertTrue(action.getSelectedPermitTile()==selectedPermitTile);
		assertTrue(action.checkIfParametersSet());
		assertEquals("b3: get bonus!", action.toString());
	}

}
