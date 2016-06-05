package modelDTO.marketDTO;

import java.io.Serializable;

import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.PermitTileDTO;
import modelDTO.playerDTO.AssistantDTO;
import server.model.gameTable.Assistant;
import server.model.gameTable.PermitTile;
import server.model.gameTable.PoliticsCard;
import server.model.market.Offer;

public class OfferDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5468761096841322975L;
	private String offeringPlayer;
	private MarketableDTO offeredObjectDTO;
	private int price;

	
	public void map(Offer realOffer) {
		if (realOffer.getOfferedObject() instanceof PoliticsCard) {
			CardColourDTO cardColourDTO=new CardColourDTO();
			PoliticsCard politicsCard=(PoliticsCard) realOffer.getOfferedObject();
			cardColourDTO.map(politicsCard.getColour());
			this.offeredObjectDTO=cardColourDTO;
		}
		if (realOffer.getOfferedObject() instanceof PermitTile) {
			PermitTileDTO permitTileDTO=new PermitTileDTO();
			permitTileDTO.map((PermitTile) realOffer.getOfferedObject());
			this.offeredObjectDTO=permitTileDTO;
		}
		if (realOffer.getOfferedObject() instanceof Assistant) {
			this.offeredObjectDTO=new AssistantDTO();
		}
		this.offeringPlayer=realOffer.getOfferingPlayer().getName();
		this.price=realOffer.getPrice();
		
	}
	
	
	public String getOfferingPlayer() {
		return this.offeringPlayer;
	}
	
	public void setOfferingPlayer(String offeringPlayer) {
		this.offeringPlayer=offeringPlayer;
	}
	
	public MarketableDTO getOfferedObjectDTO() {
		return this.offeredObjectDTO;
	}

	public void setOfferedObjectDTO(MarketableDTO offeredObjectDTO) {
		this.offeredObjectDTO = offeredObjectDTO;
	}

	public int getPrice() {
		return this.price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "Who is offering: " + offeringPlayer + ", Object: " + offeredObjectDTO
				+ ", price: " + price;
	}

}
