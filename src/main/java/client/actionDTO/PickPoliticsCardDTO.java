package client.actionDTO;

import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import model.Game;
import model.actions.Action;
import model.actions.PickPoliticsCard;

public class PickPoliticsCardDTO implements ActionDTO {

	@Override
	public Action map(Game game) {
		return new PickPoliticsCard();
	}
	
	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return null;
	}

}
