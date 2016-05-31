package server.view.notifies;

import java.util.List;

import modelDTO.clientNotifies.ClientNotify;
import modelDTO.clientNotifies.ClientPlayerDTONotify;
import modelDTO.playerDTO.ClientPlayerDTO;
import players.Player;

public class PlayerNotify implements ViewNotify {
	
	private final Player currentPlayer;
	private final List<Player> interestedPlayers;
	
	public PlayerNotify(Player currentPlayer, List<Player> interestedPlayers) {
		this.currentPlayer=currentPlayer;
		this.interestedPlayers=interestedPlayers;
	}

	@Override
	public ClientNotify toClientNotify() {
		ClientPlayerDTO clientPlayerDTO=new ClientPlayerDTO();
		clientPlayerDTO.map(currentPlayer);
		return new ClientPlayerDTONotify(clientPlayerDTO);
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
