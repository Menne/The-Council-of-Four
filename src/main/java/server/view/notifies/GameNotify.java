package server.view.notifies;

import java.util.List;

import modelDTO.GameDTO;
import modelDTO.clientNotifies.ClientNotify;
import modelDTO.clientNotifies.GameDTONotify;
import players.Player;
import server.model.Game;

public class GameNotify implements ViewNotify {
	
	private final Game game;
	private final List<Player> interestedPlayers;
	
	public GameNotify(Game game, List<Player> interestedPlayers) {
		this.game=game;
		this.interestedPlayers=interestedPlayers;
	}

	@Override
	public ClientNotify toClientNotify() {
		GameDTO gameDTO=new GameDTO();
		gameDTO.map(this.game);
		return new GameDTONotify(gameDTO);
		
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
