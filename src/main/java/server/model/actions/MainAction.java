package server.model.actions;

import server.model.Game;
import view.ErrorNotify;

/**
 * The class that models the generic main actions of the game.
 * @author Luca
 *
 */
public abstract class MainAction implements Action {
	
	public void sendErrorNotify(Game game){
		game.notifyObserver(new ErrorNotify("You can't do this action"));
	}
	
	
	public void nextState(Game game){
		game.setState(game.getState().mainActionTransition(game));
	}
}
