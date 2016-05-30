package server.model.bonus;

import server.model.Game;

/**
 * Allows the current player to take another main action
 * @author Emanuele
 *
 */

public class MainActionBonus implements Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5913736619122310756L;

	/**
	 * Increments the number of available main actions available
	 * @param game is the current game
	 */
	@Override
	public void assignBonus(Game game) {
		game.setAdditionalMainActionBonus(true);
	}

	@Override
	public String toString() {
		return "MainAction+1";
	}

}