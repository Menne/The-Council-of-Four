package server.view.notifies;

import java.util.List;

import modelDTO.clientNotifies.CityBonusDTONotify;
import modelDTO.clientNotifies.ClientNotify;
import players.Player;

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
