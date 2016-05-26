package server.model.actions;

import java.util.List;

import players.Player;
import server.model.Game;
import server.view.notifies.ErrorNotify;

/**
 * The class that models the generic quick actions of the game
 * @author Luca
 *
 */
public abstract class QuickAction implements Action {
	
	public void sendErrorNotify(Game game, List<Player> interestedPlayers){
		game.notifyObserver(new ErrorNotify("You can't do this action",interestedPlayers));
	}
	
	public void nextState(Game game){
		game.setState(game.getState().quickActionTransition(game));
	}
}