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
		PurchasedPermitTileActionDTO purchasedPermitTileActionDTO=new PurchasedPermitTileActionDTO();
	//	purchasedPermitTileActionDTO.setParser(gameDTOtoupdate).setParameters(gameDTOtoupdate.getParser());
	}

}
