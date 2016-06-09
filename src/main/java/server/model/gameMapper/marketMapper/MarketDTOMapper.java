package server.model.gameMapper.marketMapper;

import modelDTO.marketDTO.MarketDTO;
import players.Player;
import server.model.market.Market;
import server.model.market.Offer;

public class MarketDTOMapper {
	
	private OfferDTOMapper offerMapper;

	public MarketDTOMapper() {
		this.offerMapper=new OfferDTOMapper();
	}
	
	public MarketDTO map(Market realObject) {
		MarketDTO marketDTO=new MarketDTO();
		
		for (Offer realOffer : realObject.getOffersList())
			marketDTO.getOffersList().add(this.offerMapper.map(realOffer));
		for (Player realPlayer : realObject.getSellingPlayerList())
			marketDTO.getSellingPlayerList().add(realPlayer.getName());
		for (Player realPlayer : realObject.getBuyingPlayerList())
			marketDTO.getBuyingPlayerList().add(realPlayer.getName());
		
		return marketDTO;
		
	}

}
