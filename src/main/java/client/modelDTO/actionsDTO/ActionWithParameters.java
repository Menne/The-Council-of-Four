package client.modelDTO.actionsDTO;

import client.modelDTO.actionsParametersSetters.ActionParametersSetter;

/**
 * This interface provides the methods in common between an action DTO which requires parameters to be set
 * @author cg31
 *
 */
public interface ActionWithParameters extends ActionDTO {

	/**
	 * Creates the object which generates all the requests of parameters to the client view, 
	 * and which sets the parameters selected by the user to the action DTO
	 * @return the specific setter of the action
	 */
	public ActionParametersSetter setParser();

	/**
	 * Checks if the parameters in the action DTO are set properly
	 * @return true, if they are set properly, false otherwise
	 */
	public boolean checkIfParametersSet();
	
	/**
	 * When invoked, declares that the parameters of the action DTO are set properly by marking "true" 
	 * the related boolean value
	 */
	public void parametersSet();

}
