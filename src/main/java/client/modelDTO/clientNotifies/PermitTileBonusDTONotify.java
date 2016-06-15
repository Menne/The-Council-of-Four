package client.modelDTO.clientNotifies;

import client.modelDTO.GameDTO;
import client.view.notifies.ClientPermitTileBonusNotify;

public class PermitTileBonusDTONotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3601132893446445L;

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new ClientPermitTileBonusNotify());
	}

}
