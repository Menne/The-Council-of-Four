package server.view.notifies;

import java.util.List;

import client.modelDTO.clientNotifies.CityBonusDTONotify;
import client.modelDTO.clientNotifies.ClientNotify;
import server.model.player.Player;

public class CityBonusNotify implements ViewNotify {

	private List<Player> interestedPlayers;

	public CityBonusNotify(List<Player> interestedPlayers) {
		this.interestedPlayers=interestedPlayers;
	}

	@Override
	public ClientNotify toClientNotify() {
		return new CityBonusDTONotify();
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
