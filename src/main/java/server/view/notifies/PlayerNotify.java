package server.view.notifies;

import java.util.List;

import modelDTO.clientNotifies.ClientNotify;
import modelDTO.clientNotifies.ClientPlayerDTONotify;
import players.Player;
import server.model.Game;

public class PlayerNotify implements ViewNotify {
	
	private final Game game;
	private final List<Player> interestedPlayers;
	
	public PlayerNotify(Game game, List<Player> interestedPlayers) {
		this.game=game;
		this.interestedPlayers=interestedPlayers;
	}

	@Override
	public ClientNotify toClientNotify() {
		return new ClientPlayerDTONotify(this.game.getGameMapper().clientPlayerMap
				(this.game.getCurrentPlayer()));
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
