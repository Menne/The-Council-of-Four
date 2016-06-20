package client.view.notifies;

import client.view.ClientView;

public class ClientCityBonusNotify implements ClientViewNotify {

	private final int numberOfCities;
	
	public ClientCityBonusNotify(int numberOfCities) {
		this.numberOfCities=numberOfCities;
	}

	@Override
	public void updateView(ClientView view) {
		view.ChooseCityBonus(this.numberOfCities);
	}

}
