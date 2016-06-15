package client.modelDTO.clientNotifies;

import client.modelDTO.GameDTO;
import client.view.notifies.CientCityBonusNotify;

public class CityBonusDTONotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2334036584265586488L;

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new CientCityBonusNotify());
	}

}
