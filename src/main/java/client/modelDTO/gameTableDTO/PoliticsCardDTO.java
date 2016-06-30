package client.modelDTO.gameTableDTO;

import client.modelDTO.ModelDTO;
import client.modelDTO.marketDTO.MarketableDTO;

public class PoliticsCardDTO implements ModelDTO, MarketableDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6403924785270201186L;
	private CardColourDTO colour;
	
	public PoliticsCardDTO() {
	}
	
	public PoliticsCardDTO(CardColourDTO cardColourDTO) {
		this.colour=cardColourDTO;
	}

	public CardColourDTO getColour() {
		return this.colour;
	}

	public void setColour(CardColourDTO colour) {
		this.colour=colour;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colour == null) ? 0 : colour.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PoliticsCardDTO other = (PoliticsCardDTO) obj;
		if (colour == null) {
			if (other.colour != null)
				return false;
		} else if (!colour.equals(other.colour))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return colour.toString();
	}

	
}
