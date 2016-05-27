package server.view.notifies;

import java.util.List;

import modelDTO.clientNotifies.ClientNotify;
import modelDTO.clientNotifies.PlayerDTONotify;
import modelDTO.playerDTO.PlayerDTO;
import players.Player;

public class PlayerAddedNotify implements ViewNotify {
	
	private final Player player;
	private final List<Player> interestedPlayers;
	
	public PlayerAddedNotify(Player player,List<Player> interestedPlayers) {
		this.player=player;
		this.interestedPlayers=interestedPlayers;
	}
	


	public Player getPlayer() {
		return player;
	}


	@Override
	public ClientNotify toClientNotify() {
		PlayerDTO playerDTO = new PlayerDTO();
		playerDTO.map(player);
		return new PlayerDTONotify(playerDTO);
	}


	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
