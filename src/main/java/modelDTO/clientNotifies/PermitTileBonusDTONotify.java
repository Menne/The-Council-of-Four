package modelDTO.clientNotifies;

import modelDTO.GameDTO;
import modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;

public class PermitTileBonusDTONotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3601132893446445L;

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		PurchasedPermitTileActionDTO PurchasedPermitTileActionDTO=new PurchasedPermitTileActionDTO();
		PurchasedPermitTileActionDTO.setParser(gameDTOtoupdate);
	}

}
