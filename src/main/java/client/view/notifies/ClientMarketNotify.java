package client.view.notifies;

import client.modelDTO.marketDTO.MarketDTO;
import client.view.ClientView;

public class ClientMarketNotify implements ClientViewNotify {

	private MarketDTO marketUpdated;
	private final boolean startMarket;
	private final boolean closeMarket;

	public ClientMarketNotify(MarketDTO marketDTO, boolean startMarket, boolean closeMarket) {
		this.marketUpdated=marketDTO;
		this.startMarket=startMarket;
		this.closeMarket=closeMarket;
	}

	@Override
	public void updateView(ClientView view) {
		if (this.startMarket)
			view.startMarket();
		else if (this.closeMarket)
			view.closeMarket();
		else
			view.displayMarket(this.marketUpdated);
	}
	
	

}
