package client.view.notifies;

import client.modelDTO.marketDTO.MarketDTO;
import client.view.ClientView;

/**
 * This class contains the logic for notifying the ClientView that the market status is changed and needs to be updated
 * @author cg31
 *
 */
public class ClientMarketNotify implements ClientViewNotify {

	private final MarketDTO marketUpdated;
	private final boolean startMarket;
	private final boolean closeMarket;

	/**
	 * Constructor of ClientMarketNotify
	 * @param marketDTO is the updated market status
	 * @param startMarket is a flag that indicates if the market phase has just started or not
	 * @param closeMarket is a flag that indicates if the market phase has just finished or not
	 */
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
