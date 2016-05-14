package model.actions;

import model.Game;
import view.ErrorNotify;

/**
 * The class that models the generic quick actions of the game
 * @author Luca
 *
 */
public abstract class QuickAction implements Action {

	protected Game game;
	
	public QuickAction(Game game){
		this.game=game;
	}
	
	public void sendErrorNotify(){
		this.game.notifyObserver(new ErrorNotify());
	}
	
	public void nextState(){
		this.game.setState(this.game.getState().quickActionTransition());
	}
}