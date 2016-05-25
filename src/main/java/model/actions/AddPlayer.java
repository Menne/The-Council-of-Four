package model.actions;

import client.actionDTO.ActionDTO;
import model.Game;


public class AddPlayer implements Action {

	@Override
	public boolean executeAction(Game game) {
		game.addPlayer();
		return true;
	}

	@Override
	public ActionDTO map() {
		// TODO Auto-generated method stub
		return null;
	}

}
