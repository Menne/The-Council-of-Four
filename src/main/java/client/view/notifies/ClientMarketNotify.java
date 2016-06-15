package client.view.notifies;

import client.modelDTO.marketDTO.MarketDTO;
import client.view.ClientView;

public class ClientMarketNotify implements ClientViewNotify {

	private MarketDTO marketUpdated;

	public ClientMarketNotify(MarketDTO marketDTO) {
		this.marketUpdated=marketDTO;
	}

	@Override
	public void updateView(ClientView view) {
		view.displayMarket(this.marketUpdated);
	}

}
