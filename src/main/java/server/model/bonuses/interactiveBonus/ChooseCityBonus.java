package server.model.bonuses.interactiveBonus;

import java.util.Arrays;

import server.model.Game;
import server.model.actions.bonusActions.ChooseCityBonusAction;
import server.model.bonuses.Bonus;
import server.serverNotifies.AvailableActionsServerNotify;
import server.serverNotifies.GameTableServerNotify;
import server.serverNotifies.MessageServerNotify;
import server.serverNotifies.PlayerServerNotify;

/**
 * This bonus allows the current player to choose from a city in which he has already built,
 * and then assigns him the bonus/bonuses associated to the city
 * This is a particular bonus which requires a choice by the current player.
 * In order to do this the method assignBonus invokes a particular action which
 * manages the assignment of the bonus
 * @author Emanuele
 *
 */

public class ChooseCityBonus implements Bonus {
	
	private static final long serialVersionUID = 4260484811602988062L;
	private final int numberOfCities;
	
	public ChooseCityBonus(int numberOfCities) {
		this.numberOfCities=numberOfCities;
	}

	/**
	 * This method sets the state of the game to an interactive bonus state, then notifies the player 
	 * he has to choose from one of the cities in which he has built
	 * @param game is the current game status
	 */
	@Override
	public void assignBonus(Game game) {
		if (game.getCurrentPlayer().getRemainigEmporiums().size() < Game.getIntialnumberofemporiums()) {
			game.setState(game.getState().interactiveBonusTransition());
			game.notifyObserver(new GameTableServerNotify(game, Arrays.asList(game.getCurrentPlayer()),false));
			game.notifyObserver(new PlayerServerNotify(game, game.getCurrentPlayer(), 
					Arrays.asList(game.getCurrentPlayer())));
			game.notifyObserver(new AvailableActionsServerNotify(Arrays.asList(new ChooseCityBonusAction(this.numberOfCities)), Arrays.asList(game.getCurrentPlayer()),
					"Congratulations! You have the possibility to choose from " + this.numberOfCities + 
					" city/cities in which you have built an emporium, and get the related bonuses"));
		}
		else
			game.notifyObserver(new MessageServerNotify("Unable to get bonus because you haven't build any emporiums yet!", 
					Arrays.asList(game.getCurrentPlayer())));
	}

}