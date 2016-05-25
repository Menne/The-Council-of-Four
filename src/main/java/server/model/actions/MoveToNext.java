package server.model.actions;

import client.actionDTO.ActionDTO;
import client.actionDTO.MoveToNextDTO;
import server.model.Game;
import view.GameNotify;

public class MoveToNext extends QuickAction {
	
	/**
	 * If the lap is finished starts the market phase, otherwise sets the next player.
	 */
	@Override
	public boolean executeAction(Game game) {
		 
		game.setState(game.getState().moveToNextTransition(game));
		game.notifyObserver(new GameNotify(game));
		return true;
	}

	@Override
	public String toString() {
		return "ex: if you want to finish the turn";
	}

	@Override
	public ActionDTO map() {
		// TODO Auto-generated method stub
		return new MoveToNextDTO();
	}
	
	

}
