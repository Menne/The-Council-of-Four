package server.model.bonus;

import java.io.Serializable;

import server.model.Game;

/**
 * Bonus interface offers the abstract method assignBonus
 * @author andreapasquali
 * 
 */
@FunctionalInterface
public interface Bonus extends Serializable {
	/**
	 * This is an abstract assignment of a bonus
	 * @param currentPlayer is the player who earned the bonus
	 */
	public abstract void assignBonus(Game game);
}
