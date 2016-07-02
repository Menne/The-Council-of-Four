package client.modelDTO.actionsDTO;

import java.io.Serializable;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

/**
 * This interface modelizes a generic action DTO, with all the parameters DTO necessary but without logic
 * @author cg31
 *
 */
@FunctionalInterface
public interface ActionDTO extends Serializable{
	
	/**
	 * Translates the action DTO into its corresponding real action
	 * @param mapper is the the current mapper selected to translate the action
	 * @return the real action translated from the DTO object
	 */
	public Action startMapper(ActionMapperVisitor mapper);

}
