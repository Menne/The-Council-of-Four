package modelDTO.clientNotifies;

import client.view.notifies.ClientPermitTileBonusNotify;
import modelDTO.GameDTO;

public class PermitTileBonusDTONotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3601132893446445L;

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new ClientPermitTileBonusNotify
				(gameDTOtoupdate.getClientPlayer().getAvailablePermitTiles()));
	}

}
