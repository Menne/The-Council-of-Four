package client.view.notifies;

import java.util.List;

import client.modelDTO.gameTableDTO.CityDTO;
import client.view.ClientView;

public class CientCityBonusNotify implements ClientViewNotify {

	private List<CityDTO> availableCities;

	public CientCityBonusNotify(List<CityDTO> availableCities) {
		this.availableCities=availableCities;
	}

	@Override
	public void updateView(ClientView view) {
		view.ChooseCityBonus(this.availableCities);
	}

}
