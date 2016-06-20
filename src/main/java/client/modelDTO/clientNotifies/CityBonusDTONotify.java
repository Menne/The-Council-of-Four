package client.modelDTO.clientNotifies;

import client.modelDTO.GameDTO;
import client.view.notifies.ClientCityBonusNotify;

public class CityBonusDTONotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2334036584265586488L;
	private final int numberOfCities;
	
	public CityBonusDTONotify(int numberOfCities) {
		this.numberOfCities=numberOfCities;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new ClientCityBonusNotify(this.numberOfCities));
	}

}
