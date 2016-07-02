package client.modelDTO.actionsDTO;

import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

/**
 * This object is sent to the server when a client makes the decision to leave the game
 * @author cg31
 *
 */
public class QuitDTO implements ActionDTO {

	private static final long serialVersionUID = -4211998116455080382L;

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		throw new IllegalArgumentException("QuitDTO doesn't require mapping");
	}

}
