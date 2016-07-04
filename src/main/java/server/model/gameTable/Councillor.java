package server.model.gameTable;

/**
 * models the councillor; its attribute is only the colour (type CardColour)
 * @author cg31
 *
 */
public class Councillor {

	private CardColour colour;
	
	/**
	 * constructor of Councillor
	 * @param colour of the councillor
	 */
	public Councillor(CardColour colour){
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
		Councillor other = (Councillor) obj;
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