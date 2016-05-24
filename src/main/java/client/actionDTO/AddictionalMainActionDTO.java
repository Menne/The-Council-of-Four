package client.actionDTO;

import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import model.Game;
import model.actions.Action;

public class AddictionalMainActionDTO implements ActionDTO{

	@Override
	public String toString() {
		return "q4: get an additionalo main action";
	}

	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action map(Game game) {
		// TODO Auto-generated method stub
		return null;
	}	
}
