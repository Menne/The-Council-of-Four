package query;

import model.Game;
import view.AvailableActionsNotify;
import view.ViewNotify;

public class GetActions extends Query {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1194722360975978213L;

	@Override
	public ViewNotify perform(Game game) {
		return new AvailableActionsNotify(game.getState().getAcceptableActions(game));
	}

	
}
