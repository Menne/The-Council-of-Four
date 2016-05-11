package gameStuff;


/**
	 * this class models the colour of a politics card
	 * @author Emanuele
	 *
	 */
public class CardColour {

	private String colour;
	
	/**
	 * constructor of the colour of the card
	 * @param colour is the name of the colour of the card
	 */
	public CardColour(String colour) {
		this.colour=colour;	
	}
	
	public String getColour() {
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
		CardColour other = (CardColour) obj;
		if (colour == null) {
			if (other.colour != null)
				return false;
		} else if (!colour.equals(other.colour))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return colour;
	}
	
}