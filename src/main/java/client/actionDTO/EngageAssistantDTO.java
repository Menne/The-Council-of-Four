package client.actionDTO;

import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import model.Game;
import model.actions.Action;
import model.actions.standardAction.EngageAssistant;

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

	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		// TODO Auto-generated method stub
		return null;
	}

}
