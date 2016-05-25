package client.actionDTO;

import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import model.Game;
import model.actions.Action;
import model.actions.MoveToNext;

public class MoveToNextDTO implements ActionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6168986616607464860L;

	@Override
	public Action map(Game game) {
		return new MoveToNext();
	}
	
	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		// TODO Auto-generated method stub
		return null;
	}

}
