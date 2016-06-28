package server.model.bonus;

import java.util.Arrays;

import server.model.Game;
import server.view.notifies.MessageNotify;

/**
 * CoinsBonus class
 * @author Andrea, Emanuele
 *
 */

public class CoinsBonus implements Bonus{

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
	@Override
	public void assignBonus(Game game) {
		game.getCurrentPlayer().incrementCoins(coinsIncreasement);
		game.notifyObserver(new MessageNotify("Congratulations! You got a bonus and your coins are increased of " 
				+ this.coinsIncreasement, Arrays.asList(game.getCurrentPlayer())));
	}

	@Override
	public String toString() {
		return "coins+" + coinsIncreasement;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coinsIncreasement;
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
		CoinsBonus other = (CoinsBonus) obj;
		if (coinsIncreasement != other.coinsIncreasement)
			return false;
		return true;
	}

}