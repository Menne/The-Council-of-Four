package model.actions;

import client.actionDTO.ActionDTO;
import model.Game;
import view.GameNotify;


public class AddPlayer implements Action {

	@Override
	public boolean executeAction(Game game) {
		game.addPlayer();
		game.notifyObserver(new GameNotify(game));
		return true;
	}

	@Override
	public ActionDTO map() {
		// TODO Auto-generated method stub
		return null;
	}

}
