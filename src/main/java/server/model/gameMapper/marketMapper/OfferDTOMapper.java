package server.model.gameMapper.marketMapper;

import modelDTO.marketDTO.OfferDTO;
import modelDTO.playerDTO.AssistantDTO;
import server.model.gameMapper.gameTableMapper.CardColourDTOMapper;
import server.model.gameMapper.gameTableMapper.PermitTileDTOMapper;
import server.model.gameTable.Assistant;
import server.model.gameTable.PermitTile;
import server.model.gameTable.PoliticsCard;
import server.model.market.Offer;

public class OfferDTOMapper {
	
	private CardColourDTOMapper cardColourMapper;
	private PermitTileDTOMapper permitTileMapper;

	public OfferDTOMapper() {
		this.cardColourMapper=new CardColourDTOMapper();
		this.permitTileMapper=new PermitTileDTOMapper();
	}
	
	public OfferDTO map(Offer realOffer) {
		OfferDTO offerDTO=new OfferDTO();
		
		if (realOffer.getOfferedObject() instanceof PoliticsCard)
			offerDTO.setOfferedObjectDTO(this.cardColourMapper.map
					(((PoliticsCard) realOffer.getOfferedObject()).getColour()));
		if (realOffer.getOfferedObject() instanceof PermitTile)
			offerDTO.setOfferedObjectDTO(this.permitTileMapper.map
					((PermitTile) realOffer.getOfferedObject()));
		if (realOffer.getOfferedObject() instanceof Assistant)
			offerDTO.setOfferedObjectDTO(new AssistantDTO());
		
		offerDTO.setOfferingPlayer(realOffer.getOfferingPlayer().getName());
		offerDTO.setPrice(realOffer.getPrice());
		
		return offerDTO;
	}

}
