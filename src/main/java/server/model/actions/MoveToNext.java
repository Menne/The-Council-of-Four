 package server.model.actions;

import java.util.ArrayList;
import java.util.List;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.MoveToNextDTO;
import players.Player;
import server.model.Game;
import server.view.notifies.GameTableNotify;

public class MoveToNext extends QuickAction {
	
	/**
	 * If the lap is finished starts the market phase, otherwise sets the next player.
	 */
	@Override
	public boolean executeAction(Game game) {
		 
		game.setState(game.getState().moveToNextTransition(game));
		List<Player> interestedPlayers=new ArrayList<Player>();
		interestedPlayers.add(game.getCurrentPlayer());
		game.notifyObserver(new GameTableNotify(game, interestedPlayers));
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
