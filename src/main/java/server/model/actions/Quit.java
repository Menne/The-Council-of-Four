package server.model.actions;

import modelDTO.actionsDTO.ActionDTO;
import players.Player;
import server.model.Game;
import server.view.notifies.GameTableNotify;

public class Quit implements Action {

	private final Player quittingPlayer;
	
	public Quit(Player quittingPlayer) {
		this.quittingPlayer=quittingPlayer;
	}

	@Override
	public boolean executeAction(Game game) {
		game.getPlayers().remove(quittingPlayer);
		game.getQuittedPlayers().add(quittingPlayer);
		game.notifyObserver(new GameTableNotify(game, game.getPlayers()));
		return true;
	}

	@Override
	public ActionDTO map() throws IllegalStateException{
		throw new IllegalStateException("no mapping for action quit");
	}

}
