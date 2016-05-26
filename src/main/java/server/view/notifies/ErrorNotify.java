package server.view.notifies;

import java.util.List;

import modelDTO.clientNotifies.ClientNotify;
import modelDTO.clientNotifies.ErrorDTONotify;
import players.Player;

public class ErrorNotify implements ViewNotify {

	private String message;
	private List<Player> interestedPlayers;

	public ErrorNotify(String message, List<Player> interestedPlayers) {
		this.message=message;
		this.interestedPlayers=interestedPlayers;
	}

	@Override
	public ClientNotify toClientNotify() {
		return new ErrorDTONotify(this.message);
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
