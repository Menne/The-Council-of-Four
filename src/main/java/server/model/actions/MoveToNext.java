package server.model.actions;

import java.util.Arrays;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.MoveToNextDTO;
import server.model.Game;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.MessageNotify;

public class MoveToNext extends QuickAction {
	
	/**
	 * If the lap is finished starts the market phase, otherwise sets the next player.
	 */
	@Override
	public boolean executeAction(Game game) {
		game.notifyObserver(new MessageNotify("Ok, I will notify you when it will be your turn again", 
				Arrays.asList(game.getCurrentPlayer())));
		game.setState(game.getState().moveToNextTransition(game));
		game.notifyObserver(new AvailableActionsNotify(game.getState().getAcceptableActions(game), 
				Arrays.asList(game.getCurrentPlayer())));
		return true;
	}

	@Override
	public ActionDTO map() {
		return new MoveToNextDTO();
	}

}
