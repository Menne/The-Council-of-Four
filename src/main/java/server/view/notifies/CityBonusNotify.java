package server.view.notifies;

import java.util.List;

import client.modelDTO.clientNotifies.CityBonusDTONotify;
import client.modelDTO.clientNotifies.ClientNotify;
import server.model.player.Player;

public class CityBonusNotify implements ViewNotify {

	private List<Player> interestedPlayers;
	private final int numberOfCities;

	public CityBonusNotify(List<Player> interestedPlayers, int numberOfCities) {
		this.interestedPlayers=interestedPlayers;
		this.numberOfCities=numberOfCities;
	}

	@Override
	public ClientNotify toClientNotify() {
		return new CityBonusDTONotify(this.numberOfCities);
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
