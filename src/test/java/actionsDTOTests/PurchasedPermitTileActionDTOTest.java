package actionsDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.actionsParametersSetters.PurchasedPermitTileBonusParser;
import client.modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;

public class PurchasedPermitTileActionDTOTest {

	@Test
	public void testSetters() {
		PermitTileDTO selectedPermitTile= new PermitTileDTO();
		GameDTO game= new GameDTO();
		PurchasedPermitTileActionDTO action= new PurchasedPermitTileActionDTO();
		action.setPermitTile(selectedPermitTile);
		assertFalse(action.checkIfParametersSetted());
		assertEquals(PurchasedPermitTileBonusParser.class, action.setParser(null, null).getClass());
		action.parametersSetted();
		assertTrue(action.getSelectedPermitTile()==selectedPermitTile);
		assertTrue(action.checkIfParametersSetted());
		
	}

}
