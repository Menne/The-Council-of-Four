package server.model.bonus.interactiveBonus;


import java.util.Arrays;

import server.model.Game;
import server.model.bonus.Bonus;
import server.view.notifies.GameTableNotify;
import server.view.notifies.PickPermitTileBonusNotify;
import server.view.notifies.PlayerNotify;

/**
 * Pick up an uncovered permit tile without paying the cost
 * @author cg31
 *
 */

public class PickPermitTileBonus implements Bonus {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6918588158303568411L;

	/**
	 * Invokes the action associated o this bonus
	 * @param game is the current game
	 */
	@Override
	public void assignBonus(Game game) {
		game.setState(game.getState().interactiveBonusTransition());
		game.notifyObserver(new GameTableNotify(game, Arrays.asList(game.getCurrentPlayer()),false));
		game.notifyObserver(new PlayerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new PickPermitTileBonusNotify(Arrays.asList(game.getCurrentPlayer())));
	}

}