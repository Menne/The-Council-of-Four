package model.bonus;

import java.io.Serializable;

import model.Game;

/**
 * Bonus interface offers the abstract method assignBonus
 * @author andreapasquali
 * 
 */

public interface Bonus extends Serializable{
	/**
	 * This is an abstract assignment of a bonus
	 * @param currentPlayer is the player who is playing the turn
	 */
	public abstract void assignBonus(Game game);
}
