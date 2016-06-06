package server.view.notifies;

import java.util.List;

import modelDTO.clientNotifies.ClientNotify;
import modelDTO.clientNotifies.PermitTileBonusDTONotify;
import players.Player;

public class PermitTileBonusNotify implements ViewNotify {

	private List<Player> interestedPlayers;

	public PermitTileBonusNotify(List<Player> interestedPlayers) {
		this.interestedPlayers=interestedPlayers;
	}

	@Override
	public ClientNotify toClientNotify() {
		return new PermitTileBonusDTONotify();
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
