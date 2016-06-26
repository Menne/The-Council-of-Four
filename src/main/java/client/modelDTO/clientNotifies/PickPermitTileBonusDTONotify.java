package client.modelDTO.clientNotifies;

import client.modelDTO.GameDTO;
import client.view.notifies.ClientPickPermitTileBonusNotify;

public class PickPermitTileBonusDTONotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1669688750450812471L;

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new ClientPickPermitTileBonusNotify());
	}

}
