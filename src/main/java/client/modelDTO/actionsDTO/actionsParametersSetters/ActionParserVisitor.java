package client.modelDTO.actionsDTO.actionsParametersSetters;

import client.modelDTO.GameDTO;
import client.view.ClientView;

/**
 * This class provides the logic to set the needed parameters of a generic action DTO
 * @author cg31
 *
 */
@FunctionalInterface
public abstract interface ActionParserVisitor {

	/**
	 * This method sequentially tells the client view to ask the user the requested parameters 
	 * and sets them to the action DTO
	 * @param view is the current ClientView
	 * @param game is the client game
	 */
	public abstract void setParameters(ClientView view, GameDTO game);

}
