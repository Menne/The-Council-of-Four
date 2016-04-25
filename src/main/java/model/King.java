package model;

/**
 * 
 * @author andreapasquali
 *
 */
public class King {

	private City kingPresence;
	
	public King(City kingPresence){
		this.kingPresence=kingPresence;
	}
	
	
	
	public City getKingPresence() {
		return kingPresence;
	}



	/**
	 * moves king presence from the kingPresence's city to c.
	 * @param c
	 */
	public void moveKing(City c) {
		this.kingPresence=c;
	}

}