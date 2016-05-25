package model.actions;

import client.actionDTO.ActionDTO;
import client.actionDTO.AddPlayerDTO;
import model.Game;
import view.GameNotify;


public class AddPlayer implements Action {

	private String playerName;
	
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public boolean executeAction(Game game) {
		game.addPlayer(this.playerName);
		game.setState(game.getState().addPlayerTransition(game));
		game.notifyObserver(new GameNotify(game));
		return true;
	}

	@Override
	public ActionDTO map() {
		return new AddPlayerDTO();
	}

}
