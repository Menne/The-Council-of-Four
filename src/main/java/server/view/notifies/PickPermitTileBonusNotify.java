package server.view.notifies;

import java.util.List;

import client.modelDTO.clientNotifies.ClientNotify;
import client.modelDTO.clientNotifies.PickPermitTileBonusDTONotify;
import server.model.player.Player;

public class PickPermitTileBonusNotify implements ViewNotify {

	private List<Player> interestedPlayers;
	
	public PickPermitTileBonusNotify(List<Player> interestedPlayers) {
		this.interestedPlayers=interestedPlayers;
	}

	@Override
	public ClientNotify toClientNotify() {
		return new PickPermitTileBonusDTONotify();
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
