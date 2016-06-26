package client.view.notifies;

import client.view.ClientView;

public class ClientPickPermitTileBonusNotify implements ClientViewNotify {

	@Override
	public void updateView(ClientView view) {
		view.PickPermitTileBonus();
	}

}
