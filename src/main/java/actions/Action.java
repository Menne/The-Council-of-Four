package actions;

import model.Game;
/**
 * It's the class that models the generic action.
 * All the actions will operate on the protected attribute inherited from this class.
 * @author Luca
 *
 */
public abstract class Action {

	protected final Game game;
	
	public Action(Game game){
		this.game=game;
	}
	
	public abstract boolean executeAction();
}
