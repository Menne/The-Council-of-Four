package client.modelDTO.actionsDTO;

import server.model.actions.Action;
import server.model.actions.MoveToNext;
import server.model.mappers.ActionMapperVisitor;

/**
 * This object is sent to the server when a client makes the decision to skip the current turn 
 * without making any action
 * @author cg31
 *
 */
public class MoveToNextDTO implements ActionDTO {
	
	private static final long serialVersionUID = 6168986616607464860L;

	@Override
	public String toString() {
		return "sk: skip this passage";
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return new MoveToNext();
	}
	

}
