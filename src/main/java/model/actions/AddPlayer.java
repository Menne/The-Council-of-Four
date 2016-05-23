package model.actions;

import model.Game;


public class AddPlayer implements Action {

	@Override
	public boolean executeAction(Game game) {
		game.addPlayer();
		return true;
	}

}
