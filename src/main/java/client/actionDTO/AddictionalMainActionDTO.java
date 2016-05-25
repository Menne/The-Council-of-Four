package client.actionDTO;

import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import model.Game;
import model.actions.Action;
import model.actions.standardAction.AdditionalMainAction;

public class AddictionalMainActionDTO implements ActionDTO{
	
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
