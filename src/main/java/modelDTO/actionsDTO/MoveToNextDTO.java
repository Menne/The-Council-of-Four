package modelDTO.actionsDTO;

import server.model.Game;
import server.model.actions.Action;
import server.model.actions.MoveToNext;

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
	public String toString() {
		return "sk: skip this passage";
	}
	
	

}
