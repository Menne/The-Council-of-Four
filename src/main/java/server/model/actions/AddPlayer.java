package server.model.actions;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.AddPlayerDTO;
import server.model.Game;
import server.view.serverSocketViewNotifies.GameNotify;


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
