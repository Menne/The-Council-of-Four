package client.actionDTO;

import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import model.Game;
import model.actions.Action;
import model.actions.PickPoliticsCard;

public class PickPoliticsCardDTO implements ActionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7937576386571037161L;

	@Override
	public Action map(Game game) {
		return new PickPoliticsCard();
	}
	
	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return null;
	}

}
