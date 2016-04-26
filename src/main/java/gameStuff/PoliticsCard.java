package gameStuff;

	/**
	 * this class models a politics card
	 * @author Emanuele
	 *
	 */

public class PoliticsCard {
	
	CardColour colour;
	
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
	
	
}