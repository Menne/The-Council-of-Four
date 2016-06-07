package modelDTO.actionsDTO.standardActions;

import modelDTO.actionsDTO.ActionDTO;
import server.model.actions.Action;
import server.model.actions.standardActions.AdditionalMainAction;
import server.view.mapperVisitor.ActionDTOMapper;

public class AddictionalMainActionDTO implements ActionDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7297661189951116651L;
	
	
	@Override
	public String toString() {
		return "q4: get an additionalo main action";
	}

	@Override
	public Action startVisitor(ActionDTOMapper mapper) {
		return new AdditionalMainAction();
	}
	
}
