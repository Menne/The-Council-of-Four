package gameStuff;

	/**
	 * this class models a politics card
	 * @author Emanuele
	 *
	 */

public class PoliticsCard {
	
	private final CardColour colour;
	
	/**
	 * constructor of a politics card
	 * @param colour is the colour of the card (NB same colours of concillours!)
	 */
	public PoliticsCard(CardColour colour) {
		this.colour=colour;
	}
	
	public CardColour getColour() {
		return colour;
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
		PoliticsCard other = (PoliticsCard) obj;
		if (colour == null) {
			if (other.colour != null)
				return false;
		} else if (!colour.equals(other.colour))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PoliticsCard [colour=" + colour + "]";
	}
	
	
	
	
}