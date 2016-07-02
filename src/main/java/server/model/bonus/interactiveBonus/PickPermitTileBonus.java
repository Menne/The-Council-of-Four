package server.model.bonus.interactiveBonus;


import java.util.Arrays;

import server.model.Game;
import server.model.actions.bonusActions.PickPermitTileBonusAction;
import server.model.bonus.Bonus;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.GameTableNotify;
import server.view.notifies.PlayerNotify;

/**
 * Pick an uncovered permit tile without paying the cost
 * @author cg31
 *
 */
public class PickPermitTileBonus implements Bonus {
	
	private static final long serialVersionUID = -6918588158303568411L;

	/**
	 * This method sets the state of the game to an interactive bonus state, then notifies the player 
	 * he has to choose from one of the permit tiles in the game table
	 * @param game is the current game status
	 */
	@Override
	public void assignBonus(Game game) {
		game.setState(game.getState().interactiveBonusTransition());
		game.notifyObserver(new GameTableNotify(game, Arrays.asList(game.getCurrentPlayer()),false));
		game.notifyObserver(new PlayerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new AvailableActionsNotify(Arrays.asList(new PickPermitTileBonusAction()), Arrays.asList(game.getCurrentPlayer()),
				"Congratulations! You got a special bonus"));
	}

}