package model.bonus;

import model.Game;
import model.actions.AdditionalMainActionBonus;
import view.BonusNotify;

/**
 * Allows the current player to take another main action
 * @author Emanuele
 *
 */

public class MainActionBonus implements Bonus {

	/**
	 * Increments the number of available main actions available
	 * @param game is the current game
	 */
	public void assignBonus(Game game) {
		game.notifyObserver(new BonusNotify(new AdditionalMainActionBonus()));
	}

	@Override
	public String toString() {
		return "MainAction+1";
	}

}