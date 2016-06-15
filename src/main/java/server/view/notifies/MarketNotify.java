package server.view.notifies;

import java.util.List;

import client.modelDTO.clientNotifies.ClientNotify;
import client.modelDTO.clientNotifies.MarketDTONotify;
import server.model.Game;
import server.model.player.Player;

public class MarketNotify implements ViewNotify {
	
	private Game game;
	private List<Player> interestedPlayers;

	public MarketNotify(Game game, List<Player> interestedPlayers) {
		this.game=game;
		this.interestedPlayers=interestedPlayers;
	}

	@Override
	public ClientNotify toClientNotify() {
		return new MarketDTONotify(this.game.getGameMapper().marketMap
				(this.game.getMarket()));
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
