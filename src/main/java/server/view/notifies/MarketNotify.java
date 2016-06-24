package server.view.notifies;

import java.util.List;

import client.modelDTO.clientNotifies.ClientNotify;
import client.modelDTO.clientNotifies.MarketDTONotify;
import server.model.Game;
import server.model.player.Player;

public class MarketNotify implements ViewNotify {
	
	private Game game;
	private List<Player> interestedPlayers;
	private final boolean startMarket;
	private final boolean closeMarket;

	public MarketNotify(Game game, List<Player> interestedPlayers, boolean startMarket, boolean closeMarket) {
		this.game=game;
		this.interestedPlayers=interestedPlayers;
		this.startMarket=startMarket;
		this.closeMarket=closeMarket;
	}

	@Override
	public ClientNotify toClientNotify() {
		return new MarketDTONotify(this.game.getGameMapper().marketMap
				(this.game.getMarket()), this.startMarket, this.closeMarket);
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
