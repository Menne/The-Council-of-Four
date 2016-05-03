package bonus;

import model.Game;

/**
 * Bonus interface offers the abstract method assignBonus
 * @author andreapasquali
 * 
 */

public interface Bonus {
	/**
	 * This is an abstract assignment of a bonus
	 * @param currentPlayer is the player who is playing the turn
	 */
	public abstract void assignBonus(Game game);
}
