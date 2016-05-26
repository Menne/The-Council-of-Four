package server.model.bonus;

import server.model.Game;

/**
 * CoinsBonus class
 * @author Andrea, Emanuele
 *
 */

public class CoinsBonus implements Bonus{

	/**
	 * 
	 */
	private static final long serialVersionUID = 916889648569669576L;
	private final int coinsIncreasement;

	/**
	 * Is the constructor of CoinsBonus
	 * @param coinsIncreasement is the increasement of coins
	 * @throws IllegalArgumentException if coinsIncreasement is 0 or negative
	 */
	public CoinsBonus(int coinsIncreasement) throws IllegalArgumentException{
		if(coinsIncreasement<=0)
			throw new IllegalArgumentException("you can't create a coin bonus with 0 or a negative number");
		this.coinsIncreasement=coinsIncreasement;
	}
	
	/**
	 * Assigns to the current player a coinsIncreasement number of coins
	 * @param currentPlayer is the player who is playing the turn
	 */
	public void assignBonus(Game game) {
		game.getCurrentPlayer().incrementCoins(coinsIncreasement);
	}

	@Override
	public String toString() {
		return "Coins+" + coinsIncreasement;
	}

}