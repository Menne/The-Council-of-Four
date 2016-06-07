package modelDTO.actionsDTO.standardActions;

import modelDTO.actionsDTO.ActionDTO;
import server.model.actions.Action;
import server.model.actions.standardActions.EngageAssistant;
import server.view.mapperVisitor.ActionDTOMapper;

public class EngageAssistantDTO implements ActionDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4896974556695974273L;

	
	@Override
	public String toString() {
		return "q1: engage an assistant";
	}

	@Override
	public Action startVisitor(ActionDTOMapper mapper) {
		return new EngageAssistant();
	}

}
