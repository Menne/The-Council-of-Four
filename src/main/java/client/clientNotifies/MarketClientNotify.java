package client.clientNotifies;

import client.view.ClientView;

/**
 * This class contains the logic to notify the ClientView that the market status is changed and needs to be updated
 * @author cg31
 *
 */
public class MarketClientNotify implements ClientViewNotify {
	
	private final boolean startMarket;
	private final boolean closeMarket;

	/**
	 * Constructor of ClientMarketNotify
	 * @param startMarket is a flag that indicates if the market phase has just started or not
	 * @param closeMarket is a flag that indicates if the market phase has just finished or not
	 */
	public MarketClientNotify(boolean startMarket, boolean closeMarket) {
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
			view.displayMarket();
	}
	

}
