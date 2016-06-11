package server.model.bonus;

import server.model.Game;

/**
 * This bonus lets you pick one or more politics cards
 * @author Emanuele
 *
 */

public class PoliticsCardsBonus implements Bonus{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6544835437746891576L;
	private final int numberOfCards;
	
	/**
	 * constructor of PoliticsCardsBonus
	 * @param numberOfCards is the amount of cards you want to add to player's hand
	 * @throws IllegalArgumentException if number of cards is negative or 0
	 */
	public PoliticsCardsBonus(int numberOfCards) throws IllegalArgumentException{
		if(numberOfCards<=0)
			throw new IllegalArgumentException("the number of cards of a bonus can't be negative or 0");
		this.numberOfCards=numberOfCards;
	}

	/**
	 * assigns politics cards one by one to the hand of the current player
	 * @param currentPlayer is the player who is playing the turn
	 */
	@Override
	public void assignBonus(Game game) {
		for (int i=0; i<this.numberOfCards; i++)
			game.getCurrentPlayer().getHand().add(game.getGameTable().getPoliticsDeck().pickCard());
	}

	@Override
	public String toString() {
		return "PoliticsCards+" + numberOfCards;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numberOfCards;
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
		PoliticsCardsBonus other = (PoliticsCardsBonus) obj;
		if (numberOfCards != other.numberOfCards)
			return false;
		return true;
	}
	

}