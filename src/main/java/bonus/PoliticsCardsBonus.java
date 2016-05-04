package bonus;

import model.Game;

/**
 * This bonus lets you pick one or more politics cards
 * @author Emanuele
 *
 */

public class PoliticsCardsBonus implements Bonus{

	private final int numberOfCards;
	private Game game;
	
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
			game.getCurrentPlayer().getHand().add(this.game.getGameTable().getPoliticsDeck().pickCard());
	}

}