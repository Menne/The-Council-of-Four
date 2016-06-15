package client.view.notifies;

import client.view.ClientView;

public class ClientCityBonusNotify implements ClientViewNotify {

	@Override
	public void updateView(ClientView view) {
		view.ChooseCityBonus();
	}

}
