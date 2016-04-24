package bonus;

import players.Player;

/**
 * Bonus interface offers the abstract method assignBonus
 * @author andreapasquali
 * 
 */

public interface Bonus {
	/**
	 * 
	 * @param p refers to player
	 */
	public abstract void assignBonus(Player p);
}
