package client.modelDTO.marketDTO;

import java.io.Serializable;

/**
 * This class provides all the informations about an offer, but without logic
 * @author cg31
 *
 */
public class OfferDTO implements Serializable{
	
	private static final long serialVersionUID = 5468761096841322975L;
	private String offeringPlayer;
	private MarketableDTO offeredObjectDTO;
	private int price;
	
	/**
	 * @return the name of the offering player
	 */
	public String getOfferingPlayer() {
		return this.offeringPlayer;
	}
	
	/**
	 * Sets the player name to the offer DTO
	 * @param offeringPlayer
	 */
	public void setOfferingPlayer(String offeringPlayer) {
		this.offeringPlayer=offeringPlayer;
	}
	
	/**
	 * @return the offered object DTO
	 */
	public MarketableDTO getOfferedObjectDTO() {
		return this.offeredObjectDTO;
	}

	/**
	 * Sets the offered object to the offer DTO
	 * @param offeredObjectDTO is offered object DTO
	 */
	public void setOfferedObjectDTO(MarketableDTO offeredObjectDTO) {
		this.offeredObjectDTO = offeredObjectDTO;
	}

	/**
	 * @return the price of the offer DTO
	 */
	public int getPrice() {
		return this.price;
	}
	
	/**
	 * Sets the price of the offer DTO
	 * @param price is the price of the object of the offer DTO
	 */
	public void setPrice(int price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "Who is offering: " + offeringPlayer + ", Object: " + offeredObjectDTO
				+ ", price: " + price;
	}

}
