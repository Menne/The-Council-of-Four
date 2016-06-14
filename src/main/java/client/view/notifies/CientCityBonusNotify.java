package client.view.notifies;

import java.util.List;
import client.view.ClientView;
import modelDTO.gameTableDTO.CityDTO;

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
