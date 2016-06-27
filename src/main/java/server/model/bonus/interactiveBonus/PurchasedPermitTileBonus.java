package server.model.bonus.interactiveBonus;

import java.util.Arrays;

import server.model.Game;
import server.model.actions.bonusActions.PurchasedPermitTileAction;
import server.model.bonus.Bonus;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.GameTableNotify;
import server.view.notifies.MessageNotify;
import server.view.notifies.PlayerNotify;

/**
 * This bonus allows the current player to choose from a permit tile he has already picked
 * (covered or not) and then assigns him the bonus/bonuses associated to the permit tile
 * This is a particular bonus which requires a choice by the current player.
 * In order to do this the method assignBonus invokes a particular action which
 * manages the assignment of the bonus
 * @author Emanuele
 *
 */

public class PurchasedPermitTileBonus implements Bonus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5619798617816315708L;

	/**
	 * Invokes the action associated o this bonus
	 * @param game is the current game
	 */
	@Override
	public void assignBonus(Game game) {
		if (!game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().isEmpty()) {
			game.setState(game.getState().interactiveBonusTransition());
			game.notifyObserver(new GameTableNotify(game, Arrays.asList(game.getCurrentPlayer()),false));
			game.notifyObserver(new PlayerNotify(game, game.getCurrentPlayer(), 
					Arrays.asList(game.getCurrentPlayer())));
			game.notifyObserver(new AvailableActionsNotify(Arrays.asList(new PurchasedPermitTileAction()), Arrays.asList(game.getCurrentPlayer()),
					"Congratulations! You got a special bonus"));
		}
		else
			game.notifyObserver(new MessageNotify("Unable to get bonus because you don't have any permit tile!", 
					Arrays.asList(game.getCurrentPlayer())));
	}

}