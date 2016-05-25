package client.actionDTO;

import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import server.model.Game;
import server.model.actions.Action;
import server.model.actions.standardAction.AdditionalMainAction;

public class AddictionalMainActionDTO implements ActionDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7297661189951116651L;

	@Override
	public Action map(Game game) {
		return new AdditionalMainAction();
	}
	
	@Override
	public String toString() {
		return "q4: get an additionalo main action";
	}

	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
