package client.modelDTO.marketDTO;

import java.io.Serializable;

public class OfferDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5468761096841322975L;
	private String offeringPlayer;
	private MarketableDTO offeredObjectDTO;
	private int price;
	
	
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
