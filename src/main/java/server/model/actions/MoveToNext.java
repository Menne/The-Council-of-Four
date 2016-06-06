package server.model.actions;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.MoveToNextDTO;
import server.model.Game;

public class MoveToNext extends QuickAction {
	
	/**
	 * If the lap is finished starts the market phase, otherwise sets the next player.
	 */
	@Override
	public boolean executeAction(Game game) {
		
		game.setState(game.getState().moveToNextTransition(game));
		game.getState().updateClients(game);
		
		return true;
	}

	@Override
	public ActionDTO map() {
		return new MoveToNextDTO();
	}

}
