package server.model.bonuses.interactiveBonus;

import java.util.Arrays;

import server.model.Game;
import server.model.actions.bonusActions.PurchasedPermitTileAction;
import server.model.bonuses.Bonus;
import server.serverNotifies.AvailableActionsServerNotify;
import server.serverNotifies.GameTableServerNotify;
import server.serverNotifies.MessageServerNotify;
import server.serverNotifies.PlayerServerNotify;

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

	
	private static final long serialVersionUID = 5619798617816315708L;

	/**
	 * This method sets the state of the game to an interactive bonus state, then notifies the player 
	 * he has to choose from one of his permit tiles
	 * @param game is the current game status
	 */
	@Override
	public void assignBonus(Game game) {
		if (!game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().isEmpty()) {
			game.setState(game.getState().interactiveBonusTransition());
			game.notifyObserver(new GameTableServerNotify(game, Arrays.asList(game.getCurrentPlayer()),false));
			game.notifyObserver(new PlayerServerNotify(game, game.getCurrentPlayer(), 
					Arrays.asList(game.getCurrentPlayer())));
			game.notifyObserver(new AvailableActionsServerNotify(Arrays.asList(new PurchasedPermitTileAction()), Arrays.asList(game.getCurrentPlayer()),
					"Congratulations! You have the possibility to choose from one of your permit tiles,"
						+ "covered or not, and get the bonuses associated to that"));
		}
		else
			game.notifyObserver(new MessageServerNotify("Unable to get bonus because you don't have any permit tile!", 
					Arrays.asList(game.getCurrentPlayer())));
	}

}