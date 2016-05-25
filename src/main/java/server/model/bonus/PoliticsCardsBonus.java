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
	 */
	public PoliticsCardsBonus(int numberOfCards){
		this.numberOfCards=numberOfCards;
	}

	/**
	 * assigns politics cards one by one to the hand of the current player
	 * @param currentPlayer is the player who is playing the turn
	 */
	public void assignBonus(Game game) {
		for (int i=0; i<this.numberOfCards; i++)
			game.getCurrentPlayer().getHand().add(game.getGameTable().getPoliticsDeck().pickCard());
	}

	@Override
	public String toString() {
		return "PoliticsCards+" + numberOfCards;
	}

}