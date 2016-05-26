package server.model.actions;

import java.util.ArrayList;
import java.util.List;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.PickPoliticsCardDTO;
import players.Player;
import server.model.Game;
import server.view.notifies.GameNotify;

public class PickPoliticsCard implements Action {

	@Override
	public boolean executeAction(Game game) {
		game.getCurrentPlayer().getHand().add(game.getGameTable().getPoliticsDeck().pickCard());
		
		List<Player> interestedPlayers=new ArrayList<Player>();
		interestedPlayers.add(game.getCurrentPlayer());
		
		game.setState(game.getState().pickPoliticsCardTransition());
		game.notifyObserver(new GameNotify(game, interestedPlayers));
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
