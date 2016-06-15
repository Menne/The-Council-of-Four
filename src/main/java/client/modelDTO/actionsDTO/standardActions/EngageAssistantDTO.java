package client.modelDTO.actionsDTO.standardActions;

import client.modelDTO.actionsDTO.ActionDTO;
import server.model.actions.Action;
import server.model.actions.standardActions.EngageAssistant;
import server.view.actionMapperVisitor.ActionMapperVisitor;

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
	public Action startMapper(ActionMapperVisitor mapper) {
		return new EngageAssistant();
	}

}