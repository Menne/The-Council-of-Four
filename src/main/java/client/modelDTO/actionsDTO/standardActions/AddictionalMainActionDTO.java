package client.modelDTO.actionsDTO.standardActions;

import client.modelDTO.actionsDTO.ActionDTO;
import server.model.actions.Action;
import server.model.actions.standardActions.AdditionalMainAction;
import server.view.actionMapperVisitor.ActionMapperVisitor;

/**
 * This class represents the DTO version of the AdditionalMainAction action, with all the DTO parameters 
 * necessary but without logic
 * @author cg31
 *
 */
public class AddictionalMainActionDTO implements ActionDTO{
	
	private static final long serialVersionUID = 7297661189951116651L;
	
	
	@Override
	public String toString() {
		return "q4: get an additionalo main action";
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return new AdditionalMainAction();
	}
	
}
