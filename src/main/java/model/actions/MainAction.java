package model.actions;

import model.Game;
import view.ErrorNotify;

/**
 * The class that models the generic main actions of the game.
 * @author Luca
 *
 */
public abstract class MainAction implements Action {

	protected final Game game;
	
	public MainAction(Game game){
		this.game=game;
	}
	
	public void sendErrorNotify(){
		this.game.notifyObserver(new ErrorNotify("You can't do this action"));
	}
	
	public void nextState(){
		this.game.setState(this.game.getState().mainActionTransition());
	}
}
