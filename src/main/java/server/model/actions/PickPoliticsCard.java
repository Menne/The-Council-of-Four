package server.model.actions;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.PickPoliticsCardDTO;
import server.model.Game;
import server.view.serverSocketViewNotifies.GameNotify;

public class PickPoliticsCard implements Action {

	@Override
	public boolean executeAction(Game game) {
		game.getCurrentPlayer().getHand().add(game.getGameTable().getPoliticsDeck().pickCard());
		
		game.setState(game.getState().pickPoliticsCardTransition());
		game.notifyObserver(new GameNotify(game));
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
