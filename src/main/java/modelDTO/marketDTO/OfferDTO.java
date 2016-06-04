package modelDTO.marketDTO;

import java.io.Serializable;

import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.PermitTileDTO;
import modelDTO.playerDTO.AssistantDTO;
import server.model.gameTable.CardColour;
import server.model.gameTable.PermitTile;
import server.model.gameTable.PoliticsCard;
import server.model.market.Offer;

public class OfferDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5468761096841322975L;
	private String offeringPlayerDTO;
	private MarketableDTO offeredObjectDTO;
	private int price;

	
	public void map(Offer realOffer) {
		if (realOffer.getOfferedObject() instanceof PoliticsCard) {
			CardColourDTO cardColourDTO=new CardColourDTO();
				cardColourDTO.map((CardColour) realOffer.getOfferedObject());
			this.offeredObjectDTO=cardColourDTO;
		}
		if (realOffer.getOfferedObject() instanceof PermitTile) {
			PermitTileDTO permitTileDTO=new PermitTileDTO();
			permitTileDTO.map((PermitTile) realOffer.getOfferedObject());
			this.offeredObjectDTO=permitTileDTO;
		}
		if (realOffer.getOfferedObject() instanceof AssistantDTO) {
			AssistantDTO assistantDTO=new AssistantDTO();
			this.offeredObjectDTO=assistantDTO;
		}
		this.offeringPlayerDTO=realOffer.getOfferingPlayer().getName();
		this.price=realOffer.getPrice();
		
		
	}
	
	
	public String getOfferingPlayer() {
		return this.offeringPlayerDTO;
	}

	public MarketableDTO getOfferedObject() {
		return this.offeredObjectDTO;
	}

	public int getPrice() {
		return price;
	}


	@Override
	public String toString() {
		return "Who is offering: " + offeringPlayerDTO + ", Object: " + offeredObjectDTO
				+ ", price: " + price;
	}

}
