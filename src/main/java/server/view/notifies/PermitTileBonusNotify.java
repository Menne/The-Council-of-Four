package server.view.notifies;

import java.util.List;

import client.modelDTO.clientNotifies.ClientNotify;
import client.modelDTO.clientNotifies.PermitTileBonusDTONotify;
import server.model.player.Player;

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
