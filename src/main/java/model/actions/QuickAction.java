package model.actions;

import model.Game;
import view.ErrorNotify;

/**
 * The class that models the generic quick actions of the game
 * @author Luca
 *
 */
public abstract class QuickAction implements Action {
	
	public void sendErrorNotify(Game game){
		game.notifyObserver(new ErrorNotify("You can't do this action"));
	}
	
	public void nextState(Game game){
		game.setState(game.getState().quickActionTransition(game));
	}
}