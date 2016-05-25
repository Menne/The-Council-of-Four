package model.gameTable;

/**
 * modelizes the king.
 * its attribute is only the city where the king is
 * @author andreapasquali
 *
 */
public class King {

	private City kingCity;
	

	public King(City kingCity){
		this.kingCity=kingCity;
		kingCity.setIsKingPresent(true);
	}
	
	public City getKingPresence() {
		return kingCity;
	}

	
	/**
	 * Moves king presence from the kingPresence's city to c.
	 * @param c new king's city
	 */
	public void moveKing(City c) {
		this.kingCity.setIsKingPresent(false);
		this.kingCity=c;
		this.kingCity.setIsKingPresent(true);
	}

}