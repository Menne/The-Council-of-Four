package server.model.actions;

import java.util.ArrayList;
import java.util.Arrays;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.PickPoliticsCardDTO;
import players.Player;
import server.model.Game;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.GameTableNotify;
import server.view.notifies.PlayerNotify;

public class PickPoliticsCard implements Action {

	@Override
	public boolean executeAction(Game game) {
		game.getCurrentPlayer().getHand().add(game.getGameTable().getPoliticsDeck().pickCard());
		
		game.setState(game.getState().pickPoliticsCardTransition());
		
		game.notifyObserver(new GameTableNotify(game, new ArrayList<Player>(game.getPlayers())));
		game.notifyObserver(new AvailableActionsNotify(game, new ArrayList<Player>(Arrays.asList(game.getCurrentPlayer()))));
		game.notifyObserver(new PlayerNotify(game, new ArrayList<Player>(Arrays.asList(game.getCurrentPlayer()))));
		
		return true;
	}

	
	@Override
	public String toString() {
		return "pc: pick a politics card";
	}


	@Override
	public ActionDTO map() {
		return new PickPoliticsCardDTO();
	}

}
