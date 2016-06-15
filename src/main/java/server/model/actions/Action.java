package server.model.actions;

import client.modelDTO.actionsDTO.ActionDTO;
import server.model.Game;

/**
 * It's the class that models the generic action.
 * All the actions will operate on the protected attribute inherited from this class.
 * @author Luca
 *
 */
public abstract interface Action {

	
	public abstract boolean executeAction(Game game);
	
	public ActionDTO map();
}
