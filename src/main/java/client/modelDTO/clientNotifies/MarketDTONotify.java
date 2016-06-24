package client.modelDTO.clientNotifies;

import client.modelDTO.GameDTO;
import client.modelDTO.marketDTO.MarketDTO;
import client.view.notifies.ClientMarketNotify;

public class MarketDTONotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = -912988632170214482L;
	private MarketDTO updatedMarket;
	private final boolean startMarket;
	private final boolean closeMarket;
	
	public MarketDTONotify(MarketDTO marketDTO, boolean startMarket, boolean closeMarket) {
		this.updatedMarket=marketDTO;
		this.startMarket=startMarket;
		this.closeMarket=closeMarket;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.getMarket().setOffersList(this.updatedMarket.getOffersList());
		gameDTOtoupdate.getMarket().setSellingPlayerList(this.updatedMarket.getSellingPlayerList());
		gameDTOtoupdate.getMarket().setBuyingPlayerList(this.updatedMarket.getBuyingPlayerList());
		
		gameDTOtoupdate.notifyObserver(new ClientMarketNotify
				(gameDTOtoupdate.getMarket(), this.startMarket, this.closeMarket));
	}


}
