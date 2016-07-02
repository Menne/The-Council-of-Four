package server.model.gameTable;

/**
 * models the king.
 * its attribute is only the city where the king is
 * @author andreapasquali
 *
 */
public class King {

	private City kingCity;
	
	/**
	 * constructor of the king
	 * @param kingCity is the city where the king is
	 */
	public King(City kingCity){
		this.kingCity=kingCity;
	}
	
	public City getCity() {
		return kingCity;
	}

	
	/**
	 * Moves king presence from the kingPresence's city to c.
	 * @param c new king's city
	 */
	public void moveKing(City c) {
		if(c==null)
			throw new IllegalArgumentException();
		this.kingCity=c;
	}

}