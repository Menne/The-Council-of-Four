package modelDTO.actionsDTO;

import server.model.Game;
import server.model.actions.Action;
import server.model.actions.standardActions.EngageAssistant;

public class EngageAssistantDTO implements ActionDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4896974556695974273L;

	@Override
	public Action map(Game game) {
		return new EngageAssistant();
	}
	
	@Override
	public String toString() {
		return "q1: engage an assistant";
	}

}
