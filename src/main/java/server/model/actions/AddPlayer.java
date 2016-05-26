package server.model.actions;

import java.util.ArrayList;
import java.util.List;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.AddPlayerDTO;
import players.Player;
import server.model.Game;
import server.view.notifies.PlayerAddedNotify;


public class AddPlayer implements Action {

	private String playerName;
	
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public boolean executeAction(Game game) {
		
		Player player =game.addPlayer(this.playerName);
		List<Player> interestedPlayers=new ArrayList<Player>();
		interestedPlayers.add(player);
		game.notifyObserver(new PlayerAddedNotify(player,interestedPlayers));
		game.setState(game.getState().addPlayerTransition(game));
		return true;
	}

	@Override
	public ActionDTO map() {
		return new AddPlayerDTO();
	}

}
