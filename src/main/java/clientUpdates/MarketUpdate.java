package clientUpdates;

import client.clientNotifies.MarketClientNotify;
import client.modelDTO.GameDTO;
import client.modelDTO.marketDTO.MarketDTO;

/**
 * This class represents an update of the market from the server to the client
 * @author cg31
 *
 */
public class MarketUpdate implements ClientUpdate {

	private static final long serialVersionUID = -912988632170214482L;
	private final MarketDTO updatedMarket;
	private final boolean startMarket;
	private final boolean closeMarket;
	
	/**
	 * Constructor of MarketDTONotify
	 * @param marketDTO is the updated status of the market
	 * @param startMarket is a flag that indicates if the market phase has just started
	 * @param closeMarket is a flag that indicates if the market phase has just finished
	 */
	public MarketUpdate(MarketDTO marketDTO, boolean startMarket, boolean closeMarket) {
		this.updatedMarket=marketDTO;
		this.startMarket=startMarket;
		this.closeMarket=closeMarket;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.getMarket().setOffersList(this.updatedMarket.getOffersList());
		gameDTOtoupdate.getMarket().setSellingPlayerList(this.updatedMarket.getSellingPlayerList());
		gameDTOtoupdate.getMarket().setBuyingPlayerList(this.updatedMarket.getBuyingPlayerList());
		
		gameDTOtoupdate.notifyObserver(new MarketClientNotify(this.startMarket, this.closeMarket));
	}


}
