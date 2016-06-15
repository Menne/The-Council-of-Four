package client.view.notifies;

import client.view.ClientView;

public class CientCityBonusNotify implements ClientViewNotify {

	@Override
	public void updateView(ClientView view) {
		view.ChooseCityBonus();
	}

}
