package server.model.actions;

import client.modelDTO.actionsDTO.ActionDTO;
import server.model.Game;

/**
 * It's the class that models the generic action.
 * All the actions will operate on the protected attribute inherited from this class.
 * @author cg31
 *
 */
public abstract interface Action {

	/**
	 * This method executes the action selected by the user.
	 * First it checks if all the parameters are set properly, then changes the game status,
	 * then assigns the bonus, changes the current state and notifies the player the action went well.
	 * If the user selected wrong parameters, he will be notified by an error message
	 * @param game is the current game status
	 * @return true if the action went well, false otherwise
	 */
	public abstract boolean executeAction(Game game);
	
	/**
	 * Notifies the current player that the action went successfully, and updates the client game status, 
	 * and notifies the other players what action the current player decided to do
	 * @param game is the current game status
	 */
	public abstract void updateClients(Game game);
	
	/**
	 * Maps an action to its corresponding DTO object
	 * @return the action DTO corresponding to the real action
	 */
	public ActionDTO map();
}
